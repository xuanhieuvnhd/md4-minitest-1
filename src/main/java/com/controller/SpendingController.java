package com.controller;

import com.model.Category;
import com.model.Spending;
import com.model.SpendingForm;
import com.service.category.ICategoryService;
import com.service.spending.ISpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("spendings")
public class SpendingController {
    @Autowired
    private ISpendingService spendingService;
    @Autowired
    private ICategoryService categoryService;
    @Value("C:\\Users\\Administrator\\Desktop\\image\\")
    private String fileUpload;

    @ModelAttribute(name = "categories")
    private List<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping
    private ModelAndView showAllSpending(String name) {
        if (name == null) {
            List<Spending> spendings = this.spendingService.findAll();
            ModelAndView modelAndView = new ModelAndView("/spending/list");
            modelAndView.addObject("spendings", spendings);
            return modelAndView;
        } else {
            List<Spending> spendings = this.spendingService.findByName(name);
            ModelAndView modelAndView = new ModelAndView("/spending/list");
            modelAndView.addObject("spendings", spendings);
            return modelAndView;
        }
    }

    @GetMapping("/create")
    private ModelAndView showCreateForm() {
        SpendingForm spendingForm = new SpendingForm();
        ModelAndView modelAndView = new ModelAndView("/spending/create");
        modelAndView.addObject("spendingForm", spendingForm);
        return modelAndView;
    }

    @PostMapping("/create")
    private ModelAndView createSpending(@ModelAttribute SpendingForm spendingForm) {
        MultipartFile imageFile = spendingForm.getImage();
        String fileName = imageFile.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(imageFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Spending newSpending = new Spending(spendingForm.getName(), spendingForm.getPrice(), spendingForm.getNote(), fileName, spendingForm.getCategory());
        spendingService.save(newSpending);
        ModelAndView modelAndView = new ModelAndView("redirect:/spendings");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    private ModelAndView showFormEdit(@PathVariable Long id){
        Spending oldSpending = this.spendingService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/spending/edit");
        modelAndView.addObject("spending",oldSpending);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    private ModelAndView editSpending(@PathVariable Long id, @ModelAttribute SpendingForm spendingForm){
        Spending oldSpending = this.spendingService.findById(id);
        MultipartFile imageFile = spendingForm.getImage();
        String image;
        if (imageFile.getSize()==0){
            image = oldSpending.getImage();
        }else {
            String fileName = imageFile.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            image = fileName;
            try {
                FileCopyUtils.copy(imageFile.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Spending newSpending = new Spending(spendingForm.getId(),spendingForm.getName(),spendingForm.getPrice(),spendingForm.getNote(),image,spendingForm.getCategory());
        spendingService.save(newSpending);
        ModelAndView modelAndView = new ModelAndView("redirect:/spendings");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    private ModelAndView showFormDelete(@PathVariable Long id){
        Spending spending = this.spendingService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/spending/delete");
        modelAndView.addObject("spending",spending);
        return modelAndView;
    }
    @PostMapping("/delete/{id}")
    private  ModelAndView deleteSpending(@PathVariable Long id){
        Spending spending = this.spendingService.findById(id);
        File file = new File(fileUpload + spending.getImage());
        if (file.exists()){
            file.delete();
        }
        this.spendingService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/spendings");
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    private ModelAndView showSpendingDetails(@PathVariable Long id){
        Spending spending = this.spendingService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/spending/view");
        modelAndView.addObject("spending",spending);
        return modelAndView;
    }

}
