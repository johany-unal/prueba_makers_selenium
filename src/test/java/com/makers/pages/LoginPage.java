package com.makers.pages;

import com.makers.utils.ConfigReader;
import com.makers.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ── Locators ──────────────────────────────────────────────────────────────
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");
    private final By pageTitle     = By.cssSelector(".title");

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    // ── Acciones ──────────────────────────────────────────────────────────────

    public void navigate() {
        driver.get(ConfigReader.getBaseUrl());
    }

    public void enterUsername(String username) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        input.clear();
        if (username != null && !username.isEmpty()) {
            input.sendKeys(username);
        }
    }

    public void enterPassword(String password) {
        WebElement input = driver.findElement(passwordInput);
        input.clear();
        if (password != null && !password.isEmpty()) {
            input.sendKeys(password);
        }
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // ── Verificaciones ────────────────────────────────────────────────────────

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains(ConfigReader.getInventoryUrl());
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getPageTitle() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
