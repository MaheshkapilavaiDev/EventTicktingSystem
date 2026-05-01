package com.eventticktingsystem.util;

import java.io.*;
import java.util.*;

public class FileUtil {

	public static List<String> read(String file) {
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null)
				list.add(line);
		} catch (Exception ignored) {
		}
		return list;
	}

	public static void write(String file, String data) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			bw.write(data);
			bw.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void overwrite(String file, List<String> data) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (String s : data) {
				bw.write(s);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}