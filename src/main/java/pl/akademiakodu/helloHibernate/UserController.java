package pl.akademiakodu.helloHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userDao;

    @GetMapping ("/")
    public String add(ModelMap modelMap) {
        modelMap.put("user",new User());
        return "add";
    }
    @PostMapping ("/add")
    public String addUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/all";
    }

    @GetMapping ("/all")
    public String getAll(ModelMap modelMap) {
        modelMap.put("users",userDao.findAll());
        return "all";
    }

    @GetMapping("/user/{id}")
    public String update(@PathVariable Integer id, ModelMap modelMap){
        User user=userDao.findById(id).get();
        modelMap.put("user",user);
        return "add";
    }

//    @GetMapping ("/generate")
//    public String generate (ModelMap modelMap){
//        Iterable<User> users = userDao.findAll();
//        List<User> userList = new ArrayList<>();
//        users.forEach(user -> userList.add(user));
//
//        List<Pair> pairs = PairGeneration.generatePairs(userList);
//        modelMap.put("pairs",pairs);
//        return "generate";
//    }
}
