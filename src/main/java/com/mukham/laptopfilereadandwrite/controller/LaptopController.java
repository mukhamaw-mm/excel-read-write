package com.mukham.laptopfilereadandwrite.controller;

import com.mukham.laptopfilereadandwrite.model.Laptop;
import com.mukham.laptopfilereadandwrite.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class LaptopController {
    @Autowired
    LaptopService laptopService;
    @GetMapping("/loadFile")
    public List<Laptop> readFile() throws IOException {
       return laptopService.readExcelFile();
    }

    @GetMapping("/writeExcelFile")
    public List<Laptop> writeFile() throws IOException {
       return laptopService.writeExcel();
    }
    @GetMapping("/findAll")
    public List<Laptop> fillAllLaptopList(){
        return laptopService.fillAllLaptopList();
    }

    @GetMapping("/findByLaptopName")
    public List<Laptop> findByLaptopName(@RequestParam  String name){
        return laptopService.findByLaptopName(name);
    }

    @GetMapping("/delete/{id}")
    public  String deleteData(@PathVariable Long id){
       return laptopService.deleteData(id);

    }
    @GetMapping("/getLessThanPrice")
    public List<Laptop> getLessThanPrice(@RequestParam Double price){
       return laptopService.getLessThanPrice(price);
    }

    @GetMapping("/getGreaterThanPrice")
    public  List<Laptop> getGreaterThanPrice(@RequestParam  Double price){
        return laptopService.getGreaterThanPrice(price);
    }

}
