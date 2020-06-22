package com.twosigma.beakerx.autotests.java;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JavaTest extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/java/JavaTest.ipynb");
    }

    @Test(priority = 1, description = "Execute Results output contains 'BeakerxTest'.")
    public void defineJavaClass() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertEquals(txt, "BeakerxTest");
    }

    @Test(priority = 5, description = "Execute Results output contains 'TODAY:'.")
    public void callJavaClass() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertTrue(txt.contains("TODAY:"));
    }

}
