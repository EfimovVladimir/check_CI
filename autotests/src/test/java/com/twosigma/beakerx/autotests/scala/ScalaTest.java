package com.twosigma.beakerx.autotests.scala;

import com.twosigma.beakerx.autotests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScalaTest  extends BaseTest {

    int cellIndex = 0;

    @BeforeClass
    public static void setupClass() {
        BaseTest.setupClass();
        beakerxPO.runNotebookByUrl("/autotests/ipynb/scala/ScalaTest.ipynb");
    }

    @Test(priority = 1, description = "Execute Results output contains 'class Greeter'.")
    public void defineScalaClass() {
        cellIndex = 0;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsExecuteResultsSelector())
                .get(0).getText();
        Assert.assertEquals(txt, "defined class Greeter");
    }

    @Test(priority = 5, description = "Stdout output contains 'Scala developer'.")
    public void callScalaClass() {
        cellIndex++;
        WebElement codeCell = beakerxPO.runCodeCellByIndex(cellIndex);
        String txt = beakerxPO.getAllOutputsOfCodeCell(codeCell, beakerxPO.getAllOutputsStdoutSelector())
                .get(0).getText();
        Assert.assertEquals(txt, "Hello, Scala developer!");
    }
}
