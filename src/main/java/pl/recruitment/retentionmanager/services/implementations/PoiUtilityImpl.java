package pl.recruitment.retentionmanager.services.implementations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.retentionmanager.model.term.Term;
import pl.recruitment.retentionmanager.model.term.TermDto;
import pl.recruitment.retentionmanager.services.PoiUtility;
import pl.recruitment.retentionmanager.services.SystemServices;
import pl.recruitment.retentionmanager.services.TermsServices;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
            System.out.println("List in POI.create method: " + elements.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TermDto> insert = createElementList(elements);

    }

    List<TermDto> createElementList(List<String> in) {
        in.subList(0, 9).clear();
        int j = 1;
        List<Object> objectList = Arrays.asList(in.toArray());
        List<TermDto> l = new ArrayList<>();
        for (Object o : objectList) {
            TermDto term = new TermDto();
            switch (j) {
                case 1:
                    term.setActive(Boolean.parseBoolean(o.toString()));
                    System.out.println("act: " + term.isActive());
                    j++;
                    break;
                case 2:
                    term.setSystem(o.toString());
                    System.out.println("nm: " + term.getSystem());
                    j++;
                    break;
                case 3:
//                    term.setRequest(Integer.parseInt(objectList.get(i).toString().substring(0, objectList.get(i).toString().length() - 2)));
                    term.setRequest(o.toString());
                    System.out.println("rq: " + term.getRequest());
                    j++;
                    break;
                case 4:
                    term.setOrderNumber(o.toString());
                    System.out.println("on: " + term.getOrderNumber());
                    j++;
                    break;
                case 5:
                    term.setFromDate(o.toString());
                    System.out.println("from: " + term.getFromDate());
                    j++;
                    break;
                case 6:
                    term.setToDate(o.toString());
                    System.out.println("to: " + term.getToDate());
                    j++;
                    break;
                case 7:
                    term.setAmount(o.toString());
                    System.out.println("am: " + term.getAmount());
                    j++;
                    break;
                case 8:
                    term.setAmountType(o.toString());
                    System.out.println("ty: " + term.getAmount());
                    j++;
                    break;
                case 9:
                    term.setAmountPeriod(o.toString());
                    System.out.println("pr: " + term.getAmountPeriod());
                    j++;
                    break;
                case 10:
                    term.setAuthorizationPercent(o.toString());
                    System.out.println("pr: " + term.getAuthorizationPercent());
                    j = 1;
                    break;
            }
            l.add(term);
        }
        System.out.println("List in createElementList: " + l);
        System.out.println("size: " + l.size());

        return l;
    }
}
