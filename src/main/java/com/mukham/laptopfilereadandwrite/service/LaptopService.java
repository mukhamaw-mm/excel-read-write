package com.mukham.laptopfilereadandwrite.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.mukham.laptopfilereadandwrite.model.Laptop;
import com.mukham.laptopfilereadandwrite.repository.LaptopRep;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("file")

public class LaptopService {
    @Autowired
    LaptopRep laptopRep;
    @Value("${app.excelPath}")
    String file;

    public List<Laptop> readExcelFile() throws IOException {
        //String file = appConfig.getExcelPath();
        List<Laptop> laptopList = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(file));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            if (row.getRowNum() == firstSheet.getLastRowNum()) {
                break;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            Laptop laptop = new Laptop();

            while (cellIterator.hasNext()) {

                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        laptop.setLaptop_Name((String) nextCell.getStringCellValue());
                        break;
                    case 1:
                        laptop.setMouse_Name((String) nextCell.getStringCellValue());
                        break;
                    case 2:
                        laptop.setLaptop_Price((Double) nextCell.getNumericCellValue());
                        break;
                    case 3:
                        laptop.setMouse_Price((Double) nextCell.getNumericCellValue());
                        break;
                    case 4:
                        laptop.setRam((String) nextCell.getStringCellValue());
                        break;
                    case 5:
                        laptop.setProcessor((String) nextCell.getStringCellValue());
                        break;
                    case 6:
                        laptop.setStorage((String) nextCell.getStringCellValue());
                        break;

                }

            }
            laptop.setTotal(laptop.getLaptop_Price() + laptop.getMouse_Price());
            laptop.setDe(false);
            laptopList.add(laptop);
        }

        // workbook.close();
        inputStream.close();

        laptopList.stream().forEach((c) -> laptopRep.save(c));
        System.out.println(laptopList);
        return laptopList;

    }

    public List<Laptop> fillAllLaptopList() {
        List<Laptop> laptopList = laptopRep.findByDe(false);
        return laptopList;
    }

    public List<Laptop> findByLaptopName(String name) {
        List<Laptop> laptop = laptopRep.findByLaptop_Name(name);
        return laptop;

    }


    public String deleteData(Long id) {
        Optional<Laptop> laptop = laptopRep.findById(id);
        if (laptop.isPresent()) {
            Laptop lt = laptop.get();
            lt.setDe(true);
            laptopRep.save(lt);
            return "successfully deleted";
        }
        return "not found";
    }

    public List<Laptop> getLessThanPrice(Double price) {
        List<Laptop> laptopList = laptopRep.getLessThanPrice(price);
        return laptopList;
    }

    public List<Laptop> getGreaterThanPrice(Double price) {
        List<Laptop> laptopLists = laptopRep.getGreaterThanPrice(price);
        return laptopLists;


    }

    public List<Laptop> writeExcel() throws IOException {

        try {
//            String filePath = "D:\\test2.xlsx";
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            Row row = sheet.createRow(0);
            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue("laptop_Name");


            cell = row.createCell(1);
            cell.setCellValue("mouse_Name");

            cell = row.createCell(2);
            cell.setCellValue("laptop_Price");

            cell = row.createCell(3);
            cell.setCellValue("mouse_Price");

            cell = row.createCell(4);
            cell.setCellValue("ram");

            cell = row.createCell(5);
            cell.setCellValue("processor");

            cell = row.createCell(6);
            cell.setCellValue("storage");

            cell = row.createCell(7);
            cell.setCellValue("total");
            int i = 1;

            List<Laptop> laptopList = laptopRep.getLaptopByData();

            for (Laptop laptop : laptopList) {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(laptop.getLaptop_Name());

                cell = row.createCell(1);
                cell.setCellValue(laptop.getMouse_Name());

                cell = row.createCell(2);
                cell.setCellValue(laptop.getLaptop_Price());

                cell = row.createCell(3);
                cell.setCellValue(laptop.getMouse_Price());

                cell = row.createCell(4);
                cell.setCellValue(laptop.getProcessor());

                cell = row.createCell(5);
                cell.setCellValue(laptop.getRam());

                cell = row.createCell(6);
                cell.setCellValue(laptop.getStorage());

                cell = row.createCell(7);
                cell.setCellValue(laptop.getTotal());
                i++;

            }
            FileOutputStream outputStream = new FileOutputStream("D:/exceldatabase.xls");
            workbook.write(outputStream);
            outputStream.close();
            System.out.println("data is saved in excel file");

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}






