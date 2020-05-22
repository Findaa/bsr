package pl.recruitment.retentionmanager.services.implementations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.system.System;
import pl.recruitment.retentionmanager.model.term.AmountPeriod;
import pl.recruitment.retentionmanager.model.term.AmountType;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.PoiUtility;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class PoiUtilityImpl implements PoiUtility {
    @Autowired
    public PoiUtilityImpl(TermsServicesImpl terms, SystemServicesImpl systems) {
        this.terms = terms;
        this.systems = systems;
    }

    TermsServices terms;
    SystemServices systems;

    public void create(String path) {
        File csv = new File("/Users/michalcoo/IdeaProjects/javaProjects/retention-manager/src/main/resources/umowy_2016.xlsx");
        List<String> elements = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(csv);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            elements.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            elements.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            elements.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                    }
                }
            }
            java.lang.System.out.println("List in POI.create method: " + elements.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TermDto> dtos = createElementList(elements);
        java.lang.System.out.println("dtos: " + dtos);
        java.lang.System.out.println("dtos: " + dtos.get(0).getSystem() + dtos.get(0).getAmount());
        java.lang.System.out.println("dtos: " + dtos.get(1).getSystem());
        java.lang.System.out.println("dtos: " + dtos.get(2).getSystem());
        java.lang.System.out.println("size dtos: " + dtos.size());

        for (TermDto el : dtos) {
            java.lang.System.out.println("sys " + el.getSystem());
            if (systems.findAllByName(el.getSystem()) == null) {
                systems.save(new System(el.getSystem()));
            }
            Term term = new Term();
            term.setSystemName(el.getSystem());
            term.setRequest(Integer.parseInt(el.getRequest()));
            term.setOrderNumber(el.getOrderNumber());
            term.setFromDate(LocalDate.of(Integer.parseInt(el.getFromDate().substring(0, 3)),
                    Integer.parseInt(el.getFromDate().substring(5, 7)),
                    Integer.parseInt(el.getFromDate().substring(8, 10))));
            java.lang.System.out.println("Fetched date: " + el.getToDate());
            try {
                term.setToDate(LocalDate.of(Integer.parseInt(el.getToDate().substring(0, 3)),
                        Integer.parseInt(el.getToDate().substring(5, 6)),
                        Integer.parseInt(el.getToDate().substring(8, 9))));
            } catch (Exception e) {
                term.setToDate(LocalDate.of(1111, 11, 11));
                el.setToDate("1111-11-11");
                java.lang.System.out.println("Catch date: " + term.getToDate());
            }
            try{
                term.setAmount(Double.parseDouble(el.getAmount().replaceAll("[\\D&&[^.]]", "")));
            } catch(NumberFormatException nfe){
                term.setAmount(0.0);
            }
            term.setAmountType(AmountType.valueOf(el.getAmountType()));
            term.setAmountPeriod(AmountPeriod.valueOf(el.getAmountPeriod()));
            java.lang.System.out.println("Auth percent: " + Integer.parseInt(el.getAuthorizationPercent()));
            term.setAuthorizationPercent(Integer.parseInt(el.getAuthorizationPercent()));
            term.setActive(el.isActive());
            java.lang.System.out.println("Adding term " + term.getSystemName() + "1 " + term.getToDate() + "2 "
                    + "system not used" + "3 " + term.getFromDate() + "4 " + term.getOrderNumber() + "5 " + term.getRequest()
                    + "6 " + term.getAmount() + term.getAmountPeriod() + "7 " + term.getAmountType() + "8 " + term.getAuthorizationPercent() + "9 ");
            try {
                terms.save(term);
            } catch (Error e) {
                java.lang.System.out.println("Error in save");
            }
        }
    }

    List<TermDto> createElementList(List<String> in) {
        in.subList(0, 10).clear();
        int j = 1;
        List<Object> objectList = Arrays.asList(in.toArray());
        List<TermDto> l = new ArrayList<>();
        TermDto term = new TermDto();

        for (Object o : objectList) {
            switch (j) {
                case 1:
                    term.setSystem(o.toString());
                    j++;
                    break;
                case 2:
                    term.setRequest(o.toString().substring(0, o.toString().length()-2));
                    j++;
                    break;
                case 3:
                    term.setOrderNumber(o.toString());
                    j++;
                    break;
                case 4:
                    try {
                        Date javaDate = DateUtil.getJavaDate(Double.parseDouble(o.toString()));
                        term.setFromDate(new SimpleDateFormat("yyyy-MM-dd").format(javaDate));
                    } catch (NumberFormatException nfe) {
                        term.setFromDate("1111-11-11");
                        nfe.printStackTrace();
                    }
                    j++;
                    break;
                case 5:
                    try {
                        if (o.toString().contains("-")) {
                            term.setFromDate("1111-11-11");
                        } else {
                            Date javaDate = DateUtil.getJavaDate(Double.parseDouble(o.toString()));
                            term.setFromDate(new SimpleDateFormat("yyyy-MM-dd").format(javaDate));

                        }
                    } catch (NumberFormatException nfe) {
                        term.setFromDate("1111-11-11");
                        nfe.printStackTrace();
                    }
                    j++;
                    break;
                case 6:
                    term.setAmount(o.toString());
                    j++;
                    break;
                case 7:
                    term.setAmountType(o.toString());
                    j++;
                    break;
                case 8:
                    term.setAmountPeriod(o.toString());
                    j++;
                    break;
                case 9:
                    term.setAuthorizationPercent(o.toString().substring(0, o.toString().length()-2));
                    j++;
                    break;
                case 10:
                    term.setActive(Boolean.parseBoolean(o.toString()));
                    j = 1;
                    l.add(term);
                    term = new TermDto();
                    break;
            }
        }
        java.lang.System.out.println("List in createElementList: " + l);
        java.lang.System.out.println("size: " + l.size());
        return l;
    }
}
