package glue;

import account.Account;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


import java.util.*;

public class AccountSteps {

    private static final Logger logger = LogManager.getLogger(AccountSteps.class);

    private Account account;
    private String generatedStatement;


    @Given("Account exists for Acc No. {string} with Name {string}")
    public void account_exists_for_acc_no_with_name(String accNo, String name) {
        account = new Account(accNo, name);
    }

    @And("deposits are made")
    public void deposits_are_made(List<Map<String, String>> deposits) {
        logger.info("Adding deposits");
        deposits.forEach(deposit -> {
            double amount = Double.parseDouble(deposit.get("amount"));
            String label = deposit.get("label");
            account.deposit(amount, label);
        });
    }

    @And("withdrawals are made")
    public void withdrawals_are_made(List<Map<String, String>> withdrawals) {
        logger.info("Withdrawing balance");
        withdrawals.forEach(withdrawal -> {
            double amount = Double.parseDouble(withdrawal.get("amount"));
            String label = withdrawal.get("label");
            account.withdraw(amount, label);
        });
    }
    @When("statement is produced")
    public void statement_is_produced() {
        logger.info("Generating statement");
        generatedStatement = account.generateStatement();
    }

    @Then("statement includes {string}")
    public void statement_includes(String expectedDetail) {
        logger.info("Printing final statement: "+generatedStatement);
        Assert.assertTrue("Statement does not contain the expected detail: " + expectedDetail,
                generatedStatement.contains(expectedDetail));
    }


}
