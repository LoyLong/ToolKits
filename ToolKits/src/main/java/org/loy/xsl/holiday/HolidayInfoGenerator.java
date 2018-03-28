package org.loy.xsl.holiday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HolidayInfoGenerator {
	private final Map<Integer, String> marketDescMap = new HashMap<Integer, String>();
	private String outputFolder;
	private final SimpleDateFormat holidayFt = new SimpleDateFormat("dd-MMM-yy");

	private final SimpleDateFormat holidaySQLFt = new SimpleDateFormat("yyyy/MM/dd");

	private final Map<String, Map<String, List<String>>> holidaySQLSumMap = new HashMap<String, Map<String, List<String>>>();

	public static void main(String[] args) {
		BufferedReader sin = null;
		try {
			sin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please input the HolidayInput excel path : ");
			String filePatch = sin.readLine();

			if (!filePatch.endsWith(".xlsx")) {
				System.out.print("The input file path is invalid, the subfix should be 'xlsx'.");
				return;
			}

			File inputFile = new File(filePatch);
			if (!inputFile.exists()) {
				System.out.print("The input file path is invalid, it doesn't exist.");
				return;
			}

			new HolidayInfoGenerator().generateHolidayInfo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != sin)
					sin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void generateHolidayInfo(File holidayInputFile) {
		this.outputFolder = holidayInputFile.getParent();
		XSSFWorkbook xssfFileWorkBook = null;
		try {
			xssfFileWorkBook = new XSSFWorkbook(new FileInputStream(holidayInputFile));

			Iterator<?> sheetItr = xssfFileWorkBook.sheetIterator();
			while (sheetItr.hasNext()) {
				Sheet sheet = (Sheet) sheetItr.next();

				String sheetName = sheet.getSheetName().trim();

				if ("Template".equals(sheetName)) {
					readMarketDescInfo(sheet);
				}

				if ((sheetName.length() == 4) && (StringUtils.isNumeric(sheetName))) {
					generateYearSpreadSheet(sheet);
				}
			}
			outputSQLScript();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != xssfFileWorkBook)
					xssfFileWorkBook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void readMarketDescInfo(Sheet sheet) {
		boolean startMapping = false;
		Iterator<?> rowItr = sheet.rowIterator();
		while (rowItr.hasNext()) {
			Row row = (Row) rowItr.next();
			if (null == row.getCell(0)) {
				startMapping = false;
			} else {
				Cell firstCell = row.getCell(0);
				firstCell.setCellType(1);
				String firstCellValue = firstCell.getStringCellValue();

				if (!startMapping) {
					startMapping = firstCellValue.startsWith("2.");
				}

				if (startMapping) {
					Cell secoundCell = row.getCell(1);
					if (null != secoundCell) {
						secoundCell.setCellType(1);
						String secondCellValue = secoundCell.getStringCellValue();
						this.marketDescMap.put(Integer.valueOf(Integer.parseInt(firstCellValue)), secondCellValue);
					}
				}
			}
		}
	}

	private void generateYearSpreadSheet(Sheet yearSheet) {
		String year = yearSheet.getSheetName().trim();

		XSSFWorkbook holidayXlsx = new XSSFWorkbook();
		Iterator<?> rowItr = yearSheet.iterator();

		if (!rowItr.hasNext()) {
			try {
				holidayXlsx.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		Row months = (Row) rowItr.next();
		Iterator<?> cellItr = months.cellIterator();

		Map<Integer, Row> rowMap = new LinkedHashMap<Integer, Row>();
		Map<Integer, String> marketTypeMap = new HashMap<Integer, String>();
		int monthColInd = -1;
		int monthValue = -1;

		while (cellItr.hasNext()) {
			Cell monthCell = (Cell) cellItr.next();
			monthCell.setCellType(1);
			monthColInd = monthCell.getColumnIndex();
			String cellValue = monthCell.getStringCellValue();

			if (StringUtils.isNumeric(cellValue)) {
				monthValue = Integer.parseInt(cellValue);
				populateHolidaySheet(holidayXlsx, year, monthValue, monthColInd, rowMap, marketTypeMap);
			} else {
				while (rowItr.hasNext()) {
					Row marketHolidayRow = (Row) rowItr.next();
					Cell dlmCell = marketHolidayRow.getCell(0);
					dlmCell.setCellType(1);
					int dealingMode = Integer.parseInt(dlmCell.getStringCellValue());
					rowMap.put(Integer.valueOf(dealingMode), marketHolidayRow);

					Cell marketCell = marketHolidayRow.getCell(1);
					if (null != marketCell) {
						marketCell.setCellType(1);
						String marketType = marketCell.getStringCellValue();
						if (StringUtils.isNotBlank(marketType)) {
							marketTypeMap.put(Integer.valueOf(dealingMode), marketType);
						}
					}
				}
			}

		}

		outputReport(year, holidayXlsx);
	}

	private void populateHolidaySheet(XSSFWorkbook holidayXlsx, String year, int monthValue, int monthColInd, Map<Integer, Row> marketRowMap,
			Map<Integer, String> marketTypeMap) {
		String sheetName = getMonth(monthValue) + " " + year;
		XSSFSheet holidaySheet = holidayXlsx.createSheet(sheetName);

		populateTableHeader(holidayXlsx, holidaySheet);

		int iYear = Integer.parseInt(year);
        int daysInMonth = new GregorianCalendar(iYear, monthValue - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);

		Map<Integer, List<Integer>> holidayMap = new HashMap<Integer, List<Integer>>();
		for (Entry<Integer, Row> entry : marketRowMap.entrySet()) {
			int dealingMode = entry.getKey().intValue();

			Cell holidayCell = entry.getValue().getCell(monthColInd);
			holidayCell.setCellType(1);
			String holidays = holidayCell.getStringCellValue();

			if (StringUtils.isNotBlank(holidays))
				for (String holiday : holidays.split(","))
					addHoliday(holidayMap, dealingMode, holiday);
		}
		int dealingMode;
		Calendar cal;
        for (int iDay = 1; iDay <= daysInMonth; iDay++) {
			cal = Calendar.getInstance();
			cal.set(iYear, monthValue - 1, iDay);

			if (isWeekend(cal)) {
				for (Entry<Integer, Row> entry : marketRowMap.entrySet()) {
					dealingMode = entry.getKey().intValue();
					addHolidayRecordInOutputReport(holidayXlsx, holidaySheet, dealingMode, cal.getTime(), null);
					addSQLScriptRecord(year, dealingMode, cal, null);
				}

			} else {
				for (Entry<Integer, List<Integer>> entry : holidayMap.entrySet()) {
					List<Integer> holidayList = entry.getValue();
					if (holidayList.contains(Integer.valueOf(iDay))) {
						dealingMode = entry.getKey().intValue();
						String marketType = marketTypeMap.get(Integer.valueOf(dealingMode));
						addHolidayRecordInOutputReport(holidayXlsx, holidaySheet, dealingMode, cal.getTime(), marketType);
						addSQLScriptRecord(year, dealingMode, cal, marketType);
					}
				}
			}
		}

		holidaySheet.autoSizeColumn(0);
		holidaySheet.autoSizeColumn(1);
		holidaySheet.autoSizeColumn(2);
	}

	private void populateTableHeader(XSSFWorkbook holidayXlsx, XSSFSheet holidaySheet) {
		XSSFCellStyle orangeHeaderStyle = holidayXlsx.createCellStyle();
		XSSFFont headerFont = holidayXlsx.createFont();
		orangeHeaderStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());

		headerFont.setBold(true);
		orangeHeaderStyle.setFillPattern((short) 1);
		orangeHeaderStyle.setFont(headerFont);

		orangeHeaderStyle.setBorderBottom((short) 2);
		orangeHeaderStyle.setBorderTop((short) 2);
		orangeHeaderStyle.setBorderRight((short) 2);
		orangeHeaderStyle.setBorderLeft((short) 2);

		XSSFRow titleRow = holidaySheet.createRow(0);
		XSSFCell cell = titleRow.createCell(0);
		cell.setCellType(1);
		cell.setCellValue("Product Type");
		cell.setCellStyle(orangeHeaderStyle);

		cell = titleRow.createCell(1);
		cell.setCellType(1);
		cell.setCellValue("Date");
		cell.setCellStyle(orangeHeaderStyle);

		cell = titleRow.createCell(2);
		cell.setCellType(1);
		cell.setCellValue("Market / Special Holiday Type");
		cell.setCellStyle(orangeHeaderStyle);
	}

	private void addHoliday(Map<Integer, List<Integer>> holidayMap, int dealingMode, String holiday) {
		if (StringUtils.isBlank(holiday)) {
			return;
		}

		List<Integer> holidayListTemp = null;

		if (holidayMap.containsKey(Integer.valueOf(dealingMode)))
			holidayListTemp = holidayMap.get(Integer.valueOf(dealingMode));
		else {
			holidayListTemp = new ArrayList<Integer>();
		}

		holidayListTemp.add(Integer.valueOf(Integer.parseInt(holiday)));
		holidayMap.put(Integer.valueOf(dealingMode), holidayListTemp);
	}

	private boolean isWeekend(Calendar cal) {
		return (cal.get(7) == 1) || (cal.get(7) == 7);
	}

	private void addHolidayRecordInOutputReport(XSSFWorkbook holidayXlsx, XSSFSheet holidaySheet, int dealingMode, Date date, String marketType) {
		XSSFCellStyle cellStyle = holidayXlsx.createCellStyle();
		XSSFFont cellFont = holidayXlsx.createFont();
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

		cellFont.setBold(false);
		cellStyle.setFillPattern((short) 1);
		cellStyle.setFont(cellFont);

		cellStyle.setBorderBottom((short) 2);
		cellStyle.setBorderTop((short) 2);
		cellStyle.setBorderRight((short) 2);
		cellStyle.setBorderLeft((short) 2);

		int lastRow = holidaySheet.getLastRowNum() + 1;

		XSSFRow holidayRow = holidaySheet.createRow(lastRow);
		XSSFCell cell = holidayRow.createCell(0);
		cell.setCellType(1);
		cell.setCellValue(this.marketDescMap.get(Integer.valueOf(dealingMode)));
		cell.setCellStyle(cellStyle);

		cell = holidayRow.createCell(1);
		cell.setCellType(1);
		cell.setCellValue(this.holidayFt.format(date));
		cell.setCellStyle(cellStyle);

		cell = holidayRow.createCell(2);
		cell.setCellType(1);
		if (StringUtils.isBlank(marketType))
			cell.setCellValue("");
		else {
			cell.setCellValue(marketType);
		}
		cell.setCellStyle(cellStyle);
	}

	private void outputReport(String year, XSSFWorkbook holidayXlsx) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = df.format(new Date());

		String targetFilePath = this.outputFolder + File.separator + "Holiday_" + year + "_" + timeStamp + ".xlsx";
		System.out.println("output excel : " + targetFilePath);

		FileOutputStream outPutStream = null;
		try {
			outPutStream = new FileOutputStream(targetFilePath);
			holidayXlsx.write(outPutStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error eccoured during writing report to file! ");
		} finally {
			try {
				if (null != outPutStream) {
					outPutStream.close();
				}
				if (null != holidayXlsx)
					holidayXlsx.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void addSQLScriptRecord(String year, int dealingMode, Calendar date, String marketType) {
		StringBuilder sqlBdr = new StringBuilder();
		String dmStr = String.valueOf(dealingMode);
        dmStr = dmStr.length() < 2 ? "0" + dmStr : dmStr;
		String marketTypeStr = null;
        if (dealingMode < 4 || dealingMode > 10) {
            marketTypeStr = StringUtils.isBlank(marketType) ? "NT" : marketType;
        } else {
            marketTypeStr = StringUtils.isBlank(marketType) ? "" : marketType;
		}
		sqlBdr.append("insert into OESSYS..Calendar values ('").append(dmStr).append("', '").append(this.holidaySQLFt.format(date.getTime())).append("','")
				.append(marketTypeStr).append("')");

		Map<String, List<String>> yearHolidayMap = this.holidaySQLSumMap.get(year);
		if (null == yearHolidayMap) {
			yearHolidayMap = new HashMap<String, List<String>>();
			this.holidaySQLSumMap.put(year, yearHolidayMap);
		}

		List<String> dlmHolidayList = yearHolidayMap.get(dmStr);
		if (null == dlmHolidayList) {
			dlmHolidayList = new ArrayList<String>();
			yearHolidayMap.put(dmStr, dlmHolidayList);
		}

		dlmHolidayList.add(sqlBdr.toString());
	}

	private void outputSQLScript() {
		if (this.holidaySQLSumMap.isEmpty()) {
			return;
		}
		try {
			String folderPath = this.outputFolder + File.separator + "Output_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File parentFolder = new File(folderPath);

			if (!parentFolder.exists()) {
				parentFolder.mkdirs();
			}

			for (Entry<String, Map<String, List<String>>> entry : this.holidaySQLSumMap.entrySet()) {
				String year = entry.getKey();

				String yearPath = folderPath + File.separator + year;
				File yearFolder = new File(yearPath);

				if (!yearFolder.exists()) {
					yearFolder.mkdirs();
				}

				for (Entry<String, List<String>> en : entry.getValue().entrySet()) {
					String dealingMode = en.getKey();
					String fileName = yearPath + File.separator + "Calendar_" + year + "_Update_DM" + dealingMode + ".sql";
					System.out.println("Output Script file : " + fileName);
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)));
					bufferedWriter.write("use OESSYS");
					bufferedWriter.newLine();
					bufferedWriter.write("go");
					bufferedWriter.newLine();
					bufferedWriter.newLine();

					for (String sqlSrpt : en.getValue()) {
						bufferedWriter.write(sqlSrpt);
						bufferedWriter.newLine();
						bufferedWriter.write("go");
						bufferedWriter.newLine();
					}

					bufferedWriter.flush();
					bufferedWriter.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getMonth(int month) {
		String monthStr = new java.text.DateFormatSymbols().getMonths()[(month - 1)];
		return monthStr.substring(0, 3);
	}
}
