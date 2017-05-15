package nl.openweb.hippo;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import nl.openweb.hippo.domain.Node;
import nl.openweb.hippo.domain.Property;

@Controller
@RequestMapping("/")
public class AppController {

    public static final String NODE = "node";
    private JAXBContext jaxbContext;

    public AppController() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Node.class, Property.class);
    }

    @GetMapping
    public String home() {
        return "home";
    }


    @GetMapping("/result")
    public String result(HttpServletRequest request) throws IOException {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        Object nodeObj = inputFlashMap.get(NODE);
        if (nodeObj instanceof Node) {

            Map<String, Object> model = nodeToMap((Node) nodeObj);
            System.out.println(model);
        }
        return "result";
    }

    private Map<String, Object> nodeToMap(Node node) {
        Map<String, Object> result = new HashMap<>();
        List<Object> items = node.getNodeOrProperty();
        for (Object item : items) {
            if (item instanceof Property) {
                Property property = (Property) item;
                result.put((property).getName(), getValue(property));
            } else if (item instanceof Node) {
                result.put(((Node) item).getName(), nodeToMap((Node) item));
            }
        }
        return result;
    }

    private Object getValue(Property property) {
        Object result = null;
        if (property != null && property.getValue() != null && !property.getValue().isEmpty()) {

            if (property.isMultiple() != null && property.isMultiple()) {
                List<Object> list = new ArrayList<>();
                for (String v : property.getValue()) {
                    list.add(convertValue(v, property.getType()));
                }
                result = list;
            } else {
                result = convertValue(property.getValue().get(0), property.getType());
            }
        }
        return result;
    }

    private Object convertValue(String value, String type) {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        return convertUtilsBean.convert(value, getType(type));
    }

    private Class<?> getType(String type) {
        Class<?> result;
        switch (type) {
            case "Long":
                result = Long.class;
                break;
            case "Double":
                result = Double.class;
                break;
            case "Boolean":
                result = Boolean.class;
                break;
            default:
                result = String.class;
                break;
        }
        return result;
    }


    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException, JAXBException {
        String result;

        if (file.getSize() < 1024 * 1024) {
            try (InputStream inputStream = file.getInputStream()) {
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Node node = (Node) unmarshaller.unmarshal(inputStream);
                redirectAttributes.addFlashAttribute("node", node);
            }
            result = "redirect:/result";
        } else {
            redirectAttributes.addFlashAttribute("error", "The file is too big!!");
            result = "redirect:/";
        }
        return result;
    }
}
