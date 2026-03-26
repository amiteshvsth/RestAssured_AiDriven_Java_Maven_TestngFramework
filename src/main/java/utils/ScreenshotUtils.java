package utils;

import io.qameta.allure.Allure;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;

public class ScreenshotUtils {

	private static final String SCREENSHOT_DIR = "allure-results/screenshots/";

	static {
		initializeScreenshotDirectory();
	}

	private static void initializeScreenshotDirectory() {
		File dir = new File(SCREENSHOT_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static String captureScreenshot(String testName) {
		String uniqueFileName = generateUniqueFileName(testName);
		String filePath = SCREENSHOT_DIR + uniqueFileName;

		try {
			byte[] screenshotBytes = captureDesktopScreenshot(testName);
			Files.write(new File(filePath).toPath(), screenshotBytes);
			return filePath;
		} catch (IOException e) {
			Logger.error("Failed to capture screenshot: " + e.getMessage());
			return null;
		}
	}

	public static byte[] captureDesktopScreenshot(String testName) {
		try {
			Robot robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenImage = robot.createScreenCapture(screenRect);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(screenImage, "png", baos);
			return baos.toByteArray();
		} catch (Exception e) {
			Logger.error("Failed to capture desktop screenshot: " + e.getMessage());
			return new byte[0];
		}
	}

	public static void attachScreenshotToAllure(String testName) {
		String screenshotPath = captureScreenshot(testName);
		if (screenshotPath != null) {
			try {
				byte[] screenshotBytes = Files.readAllBytes(new File(screenshotPath).toPath());
				Allure.getLifecycle().addAttachment(
						"Screenshot_" + testName,
						"image/png",
						".png",
						screenshotBytes
				);
			} catch (IOException e) {
				Logger.error("Failed to attach screenshot to Allure: " + e.getMessage());
			}
		}
	}

	public static void attachScreenshotOnFailure(String testName, Throwable throwable) {
		try {
			String screenshotPath = captureScreenshot(testName + "_failure");
			if (screenshotPath != null) {
				byte[] screenshotBytes = Files.readAllBytes(new File(screenshotPath).toPath());
				Allure.getLifecycle().addAttachment(
						"Failure_Screenshot",
						"image/png",
						".png",
						screenshotBytes
				);
			}

			String failureInfo = String.format("Test Failed: %s\nError: %s\nTrace: %s",
					testName,
					throwable.getMessage(),
					getStackTrace(throwable));
			AllureUtils.addTextAttachment("Failure_Details", failureInfo);
		} catch (Exception e) {
			Logger.error("Failed to attach failure screenshot: " + e.getMessage());
		}
	}

	private static String generateUniqueFileName(String testName) {
		long threadId = Thread.currentThread().threadId();
		String sanitizedTestName = testName.replaceAll("[^a-zA-Z0-9]", "_");
		return String.format("screenshot_%s_%d_%d.png",
				sanitizedTestName,
				threadId,
				System.currentTimeMillis());
	}

	private static String getStackTrace(Throwable throwable) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : throwable.getStackTrace()) {
			sb.append(element.toString()).append("\n");
		}
		return sb.toString();
	}
}
