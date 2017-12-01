package lucci.fa.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lucci.fa.data.objects.Matchup;
import lucci.fa.data.objects.PlayerRB;
import lucci.fa.data.objects.PlayerWR;

public class FileWriter {
	
	public static void writeXLSXFile(ArrayList<Matchup> matchups) throws IOException {
		
		// absolute path to where the file should be written - use the .xlsx extension
		String excelFileName = "/Users/Mike/Desktop/Matchups.xlsx";

		XSSFWorkbook wb = new XSSFWorkbook();
		
		for (Matchup matchup : matchups) {
			XSSFSheet sheet = wb.createSheet(matchup.getAway().getName() + "@" + matchup.getHome().getName());
			
			XSSFCellStyle boldStyle = wb.createCellStyle();
			XSSFFont boldFont = wb.createFont();
			boldFont.setBold(true);
			boldStyle.setFont(boldFont);
			boldStyle.setAlignment(HorizontalAlignment.CENTER);
			
			XSSFCellStyle centerStyle = wb.createCellStyle();
			XSSFFont centerFont = wb.createFont();
			centerStyle.setFont(centerFont);
			centerStyle.setAlignment(HorizontalAlignment.CENTER);
			
			XSSFRow names = sheet.createRow(0);
			XSSFCell awayName = names.createCell(0);
			XSSFCell homeName = names.createCell(11);
			awayName.setCellValue(matchup.getAway().getName());
			homeName.setCellValue(matchup.getHome().getName());
			applyStyleToRange(sheet, boldStyle, 0, 0, 0, 11);
			
			XSSFRow records = sheet.createRow(1);
			XSSFCell awayRec = records.createCell(0);
			XSSFCell homeRec = records.createCell(11);
			awayRec.setCellValue(Integer.toString(matchup.getAway().getWin()) + '-' + Integer.toString(matchup.getAway().getLoss()));
			homeRec.setCellValue(Integer.toString(matchup.getHome().getWin()) + '-' + Integer.toString(matchup.getHome().getLoss()));
			applyStyleToRange(sheet, centerStyle, 1, 0, 1, 11);
			
			XSSFRow ranksTitle = sheet.createRow(3);
			XSSFCell awayRushOTitle = ranksTitle.createCell(0);
			XSSFCell homeRushOTitle = ranksTitle.createCell(11);
			XSSFCell awayRushDTitle = ranksTitle.createCell(1);
			XSSFCell homeRushDTitle = ranksTitle.createCell(12);
			XSSFCell awayPassOTitle = ranksTitle.createCell(2);
			XSSFCell homePassOTitle = ranksTitle.createCell(13);
			XSSFCell awayPassDTitle = ranksTitle.createCell(3);
			XSSFCell homePassDTitle = ranksTitle.createCell(14);
			awayRushOTitle.setCellValue("Offense Rushing Rank");
			homeRushOTitle.setCellValue("Offense Rushing Rank");
			awayRushDTitle.setCellValue("Defense Rushing Rank");
			homeRushDTitle.setCellValue("Defense Rushing Rank");
			awayPassOTitle.setCellValue("Offense Pass Rank");
			homePassOTitle.setCellValue("Offense Pass Rank");
			awayPassDTitle.setCellValue("Defense Pass Rank");
			homePassDTitle.setCellValue("Defense Pass Rank");		
			applyStyleToRange(sheet, boldStyle, 3, 0, 3, 14);
			
			XSSFRow ranks = sheet.createRow(4);
			XSSFCell awayRushO = ranks.createCell(0);
			XSSFCell homeRushO = ranks.createCell(11);
			XSSFCell awayRushD = ranks.createCell(1);
			XSSFCell homeRushD = ranks.createCell(12);
			XSSFCell awayPassO = ranks.createCell(2);
			XSSFCell homePassO = ranks.createCell(13);
			XSSFCell awayPassD = ranks.createCell(3);
			XSSFCell homePassD = ranks.createCell(14);
			awayRushO.setCellValue(matchup.getAway().getoRushRank());
			homeRushO.setCellValue(matchup.getHome().getoRushRank());
			awayRushD.setCellValue(matchup.getAway().getdRushRank());
			homeRushD.setCellValue(matchup.getHome().getdRushRank());
			awayPassO.setCellValue(matchup.getAway().getoPassRank());
			homePassO.setCellValue(matchup.getHome().getoPassRank());
			awayPassD.setCellValue(matchup.getAway().getdPassRank());
			homePassD.setCellValue(matchup.getHome().getdPassRank());
			applyStyleToRange(sheet, centerStyle, 4, 0, 4, 14);
			
			XSSFRow fpRanksTitle = sheet.createRow(6);
			XSSFCell awayQBRankTitle = fpRanksTitle.createCell(0);
			XSSFCell homeQBRankTitle = fpRanksTitle.createCell(11);
			XSSFCell awayRBRankTitle = fpRanksTitle.createCell(1);
			XSSFCell homeRBRankTitle = fpRanksTitle.createCell(12);
			XSSFCell awayWRRankTitle = fpRanksTitle.createCell(2);
			XSSFCell homeWRRankTitle = fpRanksTitle.createCell(13);
			XSSFCell awayTERankTitle = fpRanksTitle.createCell(3);
			XSSFCell homeTERankTitle = fpRanksTitle.createCell(14);
			XSSFCell awayKRankTitle = fpRanksTitle.createCell(4);
			XSSFCell homeKRankTitle = fpRanksTitle.createCell(15);
			XSSFCell awayDefRankTitle = fpRanksTitle.createCell(5);
			XSSFCell homeDefRankTitle = fpRanksTitle.createCell(16);
			awayQBRankTitle.setCellValue("Points to QB");
			homeQBRankTitle.setCellValue("Points to QB");
			awayRBRankTitle.setCellValue("Points to RB");
			homeRBRankTitle.setCellValue("Points to RB");
			awayWRRankTitle.setCellValue("Points to WR");
			homeWRRankTitle.setCellValue("Points to WR");
			awayTERankTitle.setCellValue("Points to TE");
			homeTERankTitle.setCellValue("Points to TE");
			awayKRankTitle.setCellValue("Points to K");
			homeKRankTitle.setCellValue("Points to K");
			awayDefRankTitle.setCellValue("Points to Def");
			homeDefRankTitle.setCellValue("Points to Def");
			applyStyleToRange(sheet, boldStyle, 6, 0, 6, 16);
			
			XSSFRow fpRanks = sheet.createRow(7);
			XSSFCell awayQBRank = fpRanks.createCell(0);
			XSSFCell homeQBRank = fpRanks.createCell(11);
			XSSFCell awayRBRank = fpRanks.createCell(1);
			XSSFCell homeRBRank = fpRanks.createCell(12);
			XSSFCell awayWRRank = fpRanks.createCell(2);
			XSSFCell homeWRRank = fpRanks.createCell(13);
			XSSFCell awayTERank = fpRanks.createCell(3);
			XSSFCell homeTERank = fpRanks.createCell(14);
			XSSFCell awayKRank = fpRanks.createCell(4);
			XSSFCell homeKRank = fpRanks.createCell(15);
			XSSFCell awayDefRank = fpRanks.createCell(5);
			XSSFCell homeDefRank = fpRanks.createCell(16);
			awayQBRank.setCellValue(matchup.getAway().getFpToQBRank());
			homeQBRank.setCellValue(matchup.getHome().getFpToQBRank());
			awayRBRank.setCellValue(matchup.getAway().getFpToRBRank());
			homeRBRank.setCellValue(matchup.getHome().getFpToRBRank());
			awayWRRank.setCellValue(matchup.getAway().getFpToWRRank());
			homeWRRank.setCellValue(matchup.getHome().getFpToWRRank());
			awayTERank.setCellValue(matchup.getAway().getFpToTERank());
			homeTERank.setCellValue(matchup.getHome().getFpToTERank());
			awayKRank.setCellValue(matchup.getAway().getFpToKRank());
			homeKRank.setCellValue(matchup.getHome().getFpToKRank());
			awayDefRank.setCellValue(matchup.getAway().getFpToDefRank());
			homeDefRank.setCellValue(matchup.getHome().getFpToDefRank());
			applyStyleToRange(sheet, centerStyle, 7, 0, 7, 16);
			
			XSSFRow qbTitle = sheet.createRow(9);
			XSSFCell awayQBNameTitle = qbTitle.createCell(0);
			XSSFCell homeQBNameTitle = qbTitle.createCell(11);
			XSSFCell awayQBAttTitle = qbTitle.createCell(1);
			XSSFCell homeQBAttTitle = qbTitle.createCell(12);
			XSSFCell awayQBCompTitle = qbTitle.createCell(2);
			XSSFCell homeQBCompTitle = qbTitle.createCell(13);
			XSSFCell awayQBYdsTitle = qbTitle.createCell(3);
			XSSFCell homeQBYdsTitle = qbTitle.createCell(14);
			XSSFCell awayQBAvgTitle = qbTitle.createCell(4);
			XSSFCell homeQBAvgTitle = qbTitle.createCell(15);
			XSSFCell awayQBYpgTitle = qbTitle.createCell(5);
			XSSFCell homeQBYpgTitle = qbTitle.createCell(16);
			XSSFCell awayQBTdTitle = qbTitle.createCell(6);
			XSSFCell homeQBTdTitle = qbTitle.createCell(17);
			XSSFCell awayQBPicksTitle = qbTitle.createCell(7);
			XSSFCell homeQBPicksTitle = qbTitle.createCell(18);
			awayQBNameTitle.setCellValue("Name");
			homeQBNameTitle.setCellValue("Name");
			awayQBAttTitle.setCellValue("Attempts");
			homeQBAttTitle.setCellValue("Attempts");
			awayQBCompTitle.setCellValue("Completions");
			homeQBCompTitle.setCellValue("Completions");
			awayQBYdsTitle.setCellValue("Yds");
			homeQBYdsTitle.setCellValue("Yds");
			awayQBAvgTitle.setCellValue("Avg");
			homeQBAvgTitle.setCellValue("Avg");
			awayQBYpgTitle.setCellValue("Ypg");
			homeQBYpgTitle.setCellValue("Ypg");
			awayQBTdTitle.setCellValue("Td");
			homeQBTdTitle.setCellValue("Td");
			awayQBPicksTitle.setCellValue("Picks");
			homeQBPicksTitle.setCellValue("Picks");
			applyStyleToRange(sheet, boldStyle, 9, 0, 9, 18);
			
			XSSFRow qb = sheet.createRow(10);
			XSSFCell awayQBName = qb.createCell(0);
			XSSFCell homeQBName = qb.createCell(11);
			XSSFCell awayQBAtt = qb.createCell(1);
			XSSFCell homeQBAtt = qb.createCell(12);
			XSSFCell awayQBComp = qb.createCell(2);
			XSSFCell homeQBComp = qb.createCell(13);
			XSSFCell awayQBYds = qb.createCell(3);
			XSSFCell homeQBYds = qb.createCell(14);
			XSSFCell awayQBAvg = qb.createCell(4);
			XSSFCell homeQBAvg = qb.createCell(15);
			XSSFCell awayQBYpg = qb.createCell(5);
			XSSFCell homeQBYpg = qb.createCell(16);
			XSSFCell awayQBTd = qb.createCell(6);
			XSSFCell homeQBTd = qb.createCell(17);
			XSSFCell awayQBPicks = qb.createCell(7);
			XSSFCell homeQBPicks = qb.createCell(18);
			awayQBName.setCellValue(matchup.getAway().getStartingQB().getName());
			homeQBName.setCellValue(matchup.getHome().getStartingQB().getName());
			awayQBAtt.setCellValue(matchup.getAway().getStartingQB().getAttempts());
			homeQBAtt.setCellValue(matchup.getHome().getStartingQB().getAttempts());
			awayQBComp.setCellValue(matchup.getAway().getStartingQB().getCompletions());
			homeQBComp.setCellValue(matchup.getHome().getStartingQB().getCompletions());
			awayQBYds.setCellValue(matchup.getAway().getStartingQB().getYards());
			homeQBYds.setCellValue(matchup.getHome().getStartingQB().getYards());
			awayQBAvg.setCellValue(matchup.getAway().getStartingQB().getAvg());
			homeQBAvg.setCellValue(matchup.getHome().getStartingQB().getAvg());
			awayQBYpg.setCellValue(matchup.getAway().getStartingQB().getYpg());
			homeQBYpg.setCellValue(matchup.getHome().getStartingQB().getYpg());
			awayQBTd.setCellValue(matchup.getAway().getStartingQB().getTd());
			homeQBTd.setCellValue(matchup.getHome().getStartingQB().getTd());
			awayQBPicks.setCellValue(matchup.getAway().getStartingQB().getPicks());
			homeQBPicks.setCellValue(matchup.getHome().getStartingQB().getPicks());
			applyStyleToRange(sheet, centerStyle, 10, 0, 10, 18);
			
			XSSFRow rbTitle = sheet.createRow(12);
			XSSFCell awayRBNameTitle = rbTitle.createCell(0);
			XSSFCell homeRBNameTitle = rbTitle.createCell(11);
			XSSFCell awayRBAttTitle = rbTitle.createCell(1);
			XSSFCell homeRBAttTitle = rbTitle.createCell(12);
			XSSFCell awayRBYdsTitle = rbTitle.createCell(2);
			XSSFCell homeRBYdsTitle = rbTitle.createCell(13);
			XSSFCell awayRBAvgTitle = rbTitle.createCell(3);
			XSSFCell homeRBAvgTitle = rbTitle.createCell(14);
			XSSFCell awayRBLongestTitle = rbTitle.createCell(4);
			XSSFCell homeRBLongestTitle = rbTitle.createCell(15);
			XSSFCell awayRBTdTitle = rbTitle.createCell(5);
			XSSFCell homeRBTdTitle = rbTitle.createCell(16);
			XSSFCell awayRBYpgTitle = rbTitle.createCell(6);
			XSSFCell homeRBYpgTitle = rbTitle.createCell(17);
			awayRBNameTitle.setCellValue("Name");
			homeRBNameTitle.setCellValue("Name");
			awayRBAttTitle.setCellValue("Att");
			homeRBAttTitle.setCellValue("Att");
			awayRBYdsTitle.setCellValue("Yds");
			homeRBYdsTitle.setCellValue("Yds");
			awayRBAvgTitle.setCellValue("Avg");
			homeRBAvgTitle.setCellValue("Avg");
			awayRBLongestTitle.setCellValue("Longest");
			homeRBLongestTitle.setCellValue("Longest");
			awayRBTdTitle.setCellValue("Td");
			homeRBTdTitle.setCellValue("Td");
			awayRBYpgTitle.setCellValue("Ypg");
			homeRBYpgTitle.setCellValue("Ypg");
			applyStyleToRange(sheet, boldStyle, 12, 0, 12, 17);
			
			int rowCounter = 13;
			int iteration = 1;
			int awayRBSize = matchup.getAway().getTopRBs().size();
			int homeRBSize = matchup.getHome().getTopRBs().size();
			
			if (awayRBSize > homeRBSize) {
				for (PlayerRB awayRB : matchup.getAway().getTopRBs()) {
					XSSFRow rb = sheet.createRow(rowCounter);
					XSSFCell awayRBName = rb.createCell(0);
					XSSFCell awayRBAtt = rb.createCell(1);
					XSSFCell awayRBYds = rb.createCell(2);
					XSSFCell awayRBAvg = rb.createCell(3);
					XSSFCell awayRBLongest = rb.createCell(4);
					XSSFCell awayRBTd = rb.createCell(5);
					XSSFCell awayRBYpg = rb.createCell(6);
					awayRBName.setCellValue(awayRB.getName());
					awayRBAtt.setCellValue(awayRB.getAttempts());
					awayRBYds.setCellValue(awayRB.getYards());
					awayRBAvg.setCellValue(awayRB.getAvg());
					awayRBLongest.setCellValue(awayRB.getLongest());
					awayRBTd.setCellValue(awayRB.getTd());
					awayRBYpg.setCellValue(awayRB.getYpg());
					
					if (iteration <= homeRBSize) {
						PlayerRB homeRB = matchup.getHome().getTopRBs().get(iteration - 1);
						XSSFCell homeRBName = rb.createCell(11);
						XSSFCell homeRBAtt = rb.createCell(12);
						XSSFCell homeRBYds = rb.createCell(13);
						XSSFCell homeRBAvg = rb.createCell(14);
						XSSFCell homeRBLongest = rb.createCell(15);
						XSSFCell homeRBTd = rb.createCell(16);
						XSSFCell homeRBYpg = rb.createCell(17);
						homeRBName.setCellValue(homeRB.getName());
						homeRBAtt.setCellValue(homeRB.getAttempts());
						homeRBYds.setCellValue(homeRB.getYards());
						homeRBAvg.setCellValue(homeRB.getAvg());
						homeRBLongest.setCellValue(homeRB.getLongest());
						homeRBTd.setCellValue(homeRB.getTd());
						homeRBYpg.setCellValue(homeRB.getYpg());
					}
					applyStyleToRange(sheet, centerStyle, rowCounter, 0, rowCounter, 17);
					rowCounter++;
					iteration++;
				}
			} else {
				for (PlayerRB homeRB : matchup.getHome().getTopRBs()) {
					XSSFRow rb = sheet.createRow(rowCounter);
					XSSFCell homeRBName = rb.createCell(11);
					XSSFCell homeRBAtt = rb.createCell(12);
					XSSFCell homeRBYds = rb.createCell(13);
					XSSFCell homeRBAvg = rb.createCell(14);
					XSSFCell homeRBLongest = rb.createCell(15);
					XSSFCell homeRBTd = rb.createCell(16);
					XSSFCell homeRBYpg = rb.createCell(17);
					homeRBName.setCellValue(homeRB.getName());
					homeRBAtt.setCellValue(homeRB.getAttempts());
					homeRBYds.setCellValue(homeRB.getYards());
					homeRBAvg.setCellValue(homeRB.getAvg());
					homeRBLongest.setCellValue(homeRB.getLongest());
					homeRBTd.setCellValue(homeRB.getTd());
					homeRBYpg.setCellValue(homeRB.getYpg());
					
					if (iteration <= awayRBSize) {
						PlayerRB awayRB = matchup.getAway().getTopRBs().get(iteration - 1);
						XSSFCell awayRBName = rb.createCell(0);
						XSSFCell awayRBAtt = rb.createCell(1);
						XSSFCell awayRBYds = rb.createCell(2);
						XSSFCell awayRBAvg = rb.createCell(3);
						XSSFCell awayRBLongest = rb.createCell(4);
						XSSFCell awayRBTd = rb.createCell(5);
						XSSFCell awayRBYpg = rb.createCell(6);
						awayRBName.setCellValue(awayRB.getName());
						awayRBAtt.setCellValue(awayRB.getAttempts());
						awayRBYds.setCellValue(awayRB.getYards());
						awayRBAvg.setCellValue(awayRB.getAvg());
						awayRBLongest.setCellValue(awayRB.getLongest());
						awayRBTd.setCellValue(awayRB.getTd());
						awayRBYpg.setCellValue(awayRB.getYpg());
					}
					applyStyleToRange(sheet, centerStyle, rowCounter, 0, rowCounter, 17);
					rowCounter++;
					iteration++;
				}
			}
			rowCounter++;
			XSSFRow WRTitle = sheet.createRow(rowCounter);
			XSSFCell awayWRNameTitle = WRTitle.createCell(0);
			XSSFCell homeWRNameTitle = WRTitle.createCell(11);
			XSSFCell awayWRRecTitle = WRTitle.createCell(1);
			XSSFCell homeWRRecTitle = WRTitle.createCell(12);
			XSSFCell awayWRTargetsTitle = WRTitle.createCell(2);
			XSSFCell homeWRTargetsTitle = WRTitle.createCell(13);
			XSSFCell awayWRYdsTitle = WRTitle.createCell(3);
			XSSFCell homeWRYdsTitle = WRTitle.createCell(14);
			XSSFCell awayWRAvgTitle = WRTitle.createCell(4);
			XSSFCell homeWRAvgTitle = WRTitle.createCell(15);
			XSSFCell awayWRTdTitle = WRTitle.createCell(5);
			XSSFCell homeWRTdTitle = WRTitle.createCell(16);
			XSSFCell awayWRLongestTitle = WRTitle.createCell(6);
			XSSFCell homeWRLongestTitle = WRTitle.createCell(17);
			XSSFCell awayWRYpgTitle = WRTitle.createCell(7);
			XSSFCell homeWRYpgTitle = WRTitle.createCell(18);
			XSSFCell awayWRYacTitle = WRTitle.createCell(8);
			XSSFCell homeWRYacTitle = WRTitle.createCell(19);
			awayWRNameTitle.setCellValue("Name");
			homeWRNameTitle.setCellValue("Name");
			awayWRRecTitle.setCellValue("Rec");
			homeWRRecTitle.setCellValue("Rec");
			awayWRTargetsTitle.setCellValue("Targets");
			homeWRTargetsTitle.setCellValue("Targets");
			awayWRYdsTitle.setCellValue("Yds");
			homeWRYdsTitle.setCellValue("Yds");
			awayWRAvgTitle.setCellValue("Avg");
			homeWRAvgTitle.setCellValue("Avg");
			awayWRTdTitle.setCellValue("Td");
			homeWRTdTitle.setCellValue("Td");
			awayWRLongestTitle.setCellValue("Longest");
			homeWRLongestTitle.setCellValue("Longest");
			awayWRYpgTitle.setCellValue("Ypg");
			homeWRYpgTitle.setCellValue("Ypg");
			awayWRYacTitle.setCellValue("Yac");
			homeWRYacTitle.setCellValue("Yac");
			applyStyleToRange(sheet, boldStyle, rowCounter, 0, rowCounter, 19);
			
			rowCounter++;
			iteration = 1;
			int awayWRSize = matchup.getAway().getTopWRs().size();
			int homeWRSize = matchup.getHome().getTopWRs().size();
			
			if (awayWRSize > homeWRSize) {
				for (PlayerWR awayWR : matchup.getAway().getTopWRs()) {
					XSSFRow wr = sheet.createRow(rowCounter);
					XSSFCell awayWRName = wr.createCell(0);
					XSSFCell awayWRRec = wr.createCell(1);
					XSSFCell awayWRTargets = wr.createCell(2);
					XSSFCell awayWRYds = wr.createCell(3);
					XSSFCell awayWRAvg = wr.createCell(4);
					XSSFCell awayWRTd = wr.createCell(5);
					XSSFCell awayWRLongest = wr.createCell(6);
					XSSFCell awayWRYpg = wr.createCell(7);
					XSSFCell awayWRYac = wr.createCell(8);
					awayWRName.setCellValue(awayWR.getName());
					awayWRRec.setCellValue(awayWR.getReceptions());
					awayWRTargets.setCellValue(awayWR.getTargets());
					awayWRYds.setCellValue(awayWR.getYards());
					awayWRAvg.setCellValue(awayWR.getAvg());
					awayWRTd.setCellValue(awayWR.getTd());
					awayWRLongest.setCellValue(awayWR.getLongest());
					awayWRYpg.setCellValue(awayWR.getYpg());
					awayWRYac.setCellValue(awayWR.getYac());
					
					if (iteration <= homeWRSize) {
						PlayerWR homeWR = matchup.getHome().getTopWRs().get(iteration - 1);
						XSSFCell homeWRName = wr.createCell(11);
						XSSFCell homeWRRec = wr.createCell(12);
						XSSFCell homeWRTargets = wr.createCell(13);
						XSSFCell homeWRYds = wr.createCell(14);
						XSSFCell homeWRAvg = wr.createCell(15);
						XSSFCell homeWRTd = wr.createCell(16);
						XSSFCell homeWRLongest = wr.createCell(17);
						XSSFCell homeWRYpg = wr.createCell(18);
						XSSFCell homeWRYac = wr.createCell(19);
						homeWRName.setCellValue(homeWR.getName());
						homeWRRec.setCellValue(homeWR.getReceptions());
						homeWRTargets.setCellValue(homeWR.getTargets());
						homeWRYds.setCellValue(homeWR.getYards());
						homeWRAvg.setCellValue(homeWR.getAvg());
						homeWRTd.setCellValue(homeWR.getTd());
						homeWRLongest.setCellValue(homeWR.getLongest());
						homeWRYpg.setCellValue(homeWR.getYpg());
						homeWRYac.setCellValue(homeWR.getYac());
					}
					applyStyleToRange(sheet, centerStyle, rowCounter, 0, rowCounter, 19);
					rowCounter++;
					iteration++;
				}
			} else {
				for (PlayerWR homeWR : matchup.getHome().getTopWRs()) {
					XSSFRow wr = sheet.createRow(rowCounter);
					XSSFCell homeWRName = wr.createCell(11);
					XSSFCell homeWRRec = wr.createCell(12);
					XSSFCell homeWRTargets = wr.createCell(13);
					XSSFCell homeWRYds = wr.createCell(14);
					XSSFCell homeWRAvg = wr.createCell(15);
					XSSFCell homeWRTd = wr.createCell(16);
					XSSFCell homeWRLongest = wr.createCell(17);
					XSSFCell homeWRYpg = wr.createCell(18);
					XSSFCell homeWRYac = wr.createCell(19);
					homeWRName.setCellValue(homeWR.getName());
					homeWRRec.setCellValue(homeWR.getReceptions());
					homeWRTargets.setCellValue(homeWR.getTargets());
					homeWRYds.setCellValue(homeWR.getYards());
					homeWRAvg.setCellValue(homeWR.getAvg());
					homeWRTd.setCellValue(homeWR.getTd());
					homeWRLongest.setCellValue(homeWR.getLongest());
					homeWRYpg.setCellValue(homeWR.getYpg());
					homeWRYac.setCellValue(homeWR.getYac());
					
					if (iteration <= awayWRSize) {
						PlayerWR awayWR = matchup.getAway().getTopWRs().get(iteration - 1);
						XSSFCell awayWRName = wr.createCell(0);
						XSSFCell awayWRRec = wr.createCell(1);
						XSSFCell awayWRTargets = wr.createCell(2);
						XSSFCell awayWRYds = wr.createCell(3);
						XSSFCell awayWRAvg = wr.createCell(4);
						XSSFCell awayWRTd = wr.createCell(5);
						XSSFCell awayWRLongest = wr.createCell(6);
						XSSFCell awayWRYpg = wr.createCell(7);
						XSSFCell awayWRYac = wr.createCell(8);
						awayWRName.setCellValue(awayWR.getName());
						awayWRRec.setCellValue(awayWR.getReceptions());
						awayWRTargets.setCellValue(awayWR.getTargets());
						awayWRYds.setCellValue(awayWR.getYards());
						awayWRAvg.setCellValue(awayWR.getAvg());
						awayWRTd.setCellValue(awayWR.getTd());
						awayWRLongest.setCellValue(awayWR.getLongest());
						awayWRYpg.setCellValue(awayWR.getYpg());
						awayWRYac.setCellValue(awayWR.getYac());
					}
					applyStyleToRange(sheet, centerStyle, rowCounter, 0, rowCounter, 19);
					rowCounter++;
					iteration++;
				}
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(excelFileName);
			wb.write(fileOut);
		} catch(Exception e) {
			throw new IOException(e);
		} finally {
			if (fileOut != null) {
				fileOut.flush();
				fileOut.close();
			}
			if (wb != null) {
				wb.close();
			}
		}
	}
	
	public static void applyStyleToRange(XSSFSheet sheet, XSSFCellStyle style, int rowStart, int colStart, int rowEnd, int colEnd) {
	    for (int r = rowStart; r <= rowEnd; r++) {
	        for (int c = colStart; c <= colEnd; c++) {
	            XSSFRow row = sheet.getRow(r);

	            if (row != null) {
	                XSSFCell cell = row.getCell(c);

	                if (cell != null) {
	                    cell.setCellStyle(style);
	                }
	            }
	        }
	    }
	}
}
