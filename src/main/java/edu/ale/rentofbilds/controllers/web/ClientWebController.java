package edu.ale.rentofbilds.controllers.web;

import edu.ale.rentofbilds.data.FakeData;
import edu.ale.rentofbilds.form.ClientForm;
import edu.ale.rentofbilds.model.Client;
import edu.ale.rentofbilds.model.Item;
import edu.ale.rentofbilds.service.client.impls.CrudClientServiceFakeImpl;
import edu.ale.rentofbilds.service.client.impls.CrudClientServiceMongoImpl;
import edu.project.rent.forms.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/web/client")
public class ClientWebController {

    @Autowired
    CrudClientServiceMongoImpl service;

    @RequestMapping("/list")
    String getList(Model model) {
        model.addAttribute("clients", service.getAll());
        return "clientsTable";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        ClientForm clientForm = new ClientForm();
        model.addAttribute("form", clientForm);
        return "clientAddForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("form") ClientForm clientForm, Model model) {
        Client client = new Client();
        client.setName(clientForm.getName());
        client.setAdress(clientForm.getAdress());
        client.setPhone(clientForm.getPhone());
        client.setGender(clientForm.getGender());
        String birthdayAsString = clientForm.getDateOfBirthday();
//        client.setDateOfBirthday(clientForm.getDateOfBirthday());
        LocalDate birthdayAsDate = LocalDate.parse(birthdayAsString);
        client.setDateOfBirthday(birthdayAsDate);
        client.setDescription(clientForm.getDescription());
        service.create(client);
        return "redirect:/web/client/list";
    }


    @RequestMapping("/delete/{id}")
    String deleteById(@PathVariable("id") String id) {
        System.out.println(" Why are u delete me ?!");
        service.delete(id);
        return "redirect:/web/client/list";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable("id") String id) {
        Client client = service.get(id);
        ClientForm clientForm = new ClientForm();
        clientForm.setId(client.getId());
        clientForm.setName(client.getName());
        clientForm.setGender(client.getGender());
        clientForm.setAdress(client.getAdress());
        clientForm.setPhone(client.getPhone());
        clientForm.setDateOfBirthday(client.getDateOfBirthday().toString());
        clientForm.setDescription(client.getDescription());
        model.addAttribute("form", clientForm);
        return "updateClient";

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable("id") String id, @ModelAttribute("form") ClientForm clientForm) {
        Client client = service.get(id);
        client.setName(clientForm.getName());
        client.setGender(clientForm.getGender());
        client.setAdress(clientForm.getAdress());
        client.setPhone(clientForm.getPhone());
        String birthdayAsString = clientForm.getDateOfBirthday();
//        client.setDateOfBirthday(clientForm.getDateOfBirthday());
        LocalDate birthdayAsDate = LocalDate.parse(birthdayAsString);
        client.setDateOfBirthday(birthdayAsDate);
        client.setDescription(client.getDescription());
        service.update(client);
        return "redirect:/web/client/list";
    }

    @RequestMapping(value = "/list/sort/name", method = RequestMethod.GET)
    public String sortedByName(Model model) {
        model.addAttribute("clients", service.getAllSortedByName());
        return "clientsTable";
    }

    @RequestMapping(value = "/list/sort/birthday", method = RequestMethod.GET)
    public String sortedByModified(Model model) {
        model.addAttribute("clients", service.getAllSortedByBirthday());
        return "clientsTable";
    }

    @RequestMapping(value = "/list/sort/id", method = RequestMethod.GET)
    public String sortedById(Model model) {
        model.addAttribute("clients", service.getAllSortedById());
        return "clientsTable";
    }

    @RequestMapping(value = "/list/sort/phone", method = RequestMethod.GET)
    public String sortedByPhone(Model model) {
        model.addAttribute("clients", service.getAllSortedByPhone());
        return "clientsTable";
    }

}


