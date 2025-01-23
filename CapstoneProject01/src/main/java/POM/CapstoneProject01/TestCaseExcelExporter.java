package POM.CapstoneProject01;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCaseExcelExporter {
	
	String filePath = "C:\\Maniish_Automation\\TestCases.xlsx";

	
	public static void createTestCaseExcel(String filePath) throws IOException {
        // Create a new workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
        
        // Create a sheet in the workbook
        XSSFSheet sheet = workbook.createSheet("Test Cases");
        
        // Create the header row
        Row headerRow = sheet.getRow(0);
        headerRow.createCell(0).setCellValue("Test Case ID");
        headerRow.createCell(1).setCellValue("Test Case Description");
        headerRow.createCell(2).setCellValue("Preconditions");
        headerRow.createCell(3).setCellValue("Test Steps");
        headerRow.createCell(4).setCellValue("Expected Result");
        headerRow.createCell(5).setCellValue("Actual Result");
        headerRow.createCell(6).setCellValue("Status (Pass/Fail)");

        // Add test cases to the sheet
        Object[][] testCases = {
                {"TC_01", "Login with valid user", "User is on login page", "1. Enter username\n2. Enter password\n3. Click login button", "User should be redirected to HomePage", "", ""},
                {"TC_02", "Login with invalid user", "User is on login page", "1. Enter invalid username\n2. Enter invalid password\n3. Click login button", "Error message should be displayed", "", ""},
                {"TC_03", "Search Hotel will all mandatory fields", "User is on the search hotel page", "1. Enter all hotel values\n2. Click search button", "Search results should be displayed", "", ""},
                {"TC_04", "Search Hotel without mandatory fields", "User is on the search hotel page", "1. Enter values without few mandatory\n2. Click search button", "Error message should should be displayed", "", ""},
                {"TC_05", "Search Hotel and click cancel", "User should filter the hotel based on values passed", "1. Filter hotel based on condition\n2. Click cancel button", "User should redirect to search page", "", ""},
                {"TC_06", "Search Hotel without mandatory fields", "User should filter the hotel based on values passed", "1. Filter hotel based on condition\n2. Click continue button", "User should redirect to book hotel confirm page", "", ""},
                {"TC_07", "Book Hotel without mandatory fields", "User should filter and select the hotel based on values passed", "1. Confirm booking without few mandatory fields\n2. Click confirm button", "Error message should be displayed", "", ""},
                {"TC_08", "Book Hotel with all fields", "User should filter and select the hotel based on values passed", "1. Enter all fields to confirm booking\n2. Click confirm button", "User should redirect to book hotel itinerary page", "", ""},
                {"TC_09", "Test cancel selected option in book itinerary page", "User should select and confirm hotel booking based on values passed", "1. Select the booked hotel check box\n2. Click cancel selected button", "Selected hotel booking should be canceled", "", ""},
                {"TC_10", "Test search order field in book itinerary page", "User should select and confirm hotel booking based on values passed", "1. Get the order id of the selected hotel booking\n2. Pass the order in in search order id text box and search", "Only searched order id result should display", "", ""},
                {"TC_11", "Test show all link after searched with one order id in book itinerary page", "User should select and confirm hotel booking based on values passed", "1. After getting one result for the searched order id\n2. click show all link which displayed on the page", "All hotel bookings will be display", "", ""},
                {"TC_12", "Test cancel hotel booking button in the booking record", "User should select and confirm hotel booking based on values passed", "1. User can see the cancel button with order id for the respecting hotel booking\n2. click any of the cancel button with order id", "Respective hotel booking will be cancelled", "", ""},
                {"TC_12", "Check Logout", "Click the logout button after verifying the hotel booking", "1. User can see the logout button in the book itinerary page\n2. click logout button", "User should logged out from the website", "", ""}
        };
        
        // Populate the sheet with test case data
        int rowNum = 1;
        for (Object[] testCase : testCases) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : testCase) {
                row.createCell(colNum++).setCellValue((String) field);
            }
        }

        // Create the file
        try (FileOutputStream fileOut = new FileOutputStream(new String(filePath))) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }

    public static void main(String[] args) {
        try {
            createTestCaseExcel("TestCases.xlsx");
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
