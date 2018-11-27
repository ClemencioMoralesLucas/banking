package com.clemencio.morales.banking;

import org.testng.annotations.Test;
import utils.EnumTest;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class BankAccountTypeTest {

    private static final List<String> EXPECTED_VALUES = Arrays.asList("SAVING",
            "CHECKING");

    @Test
    public void testBankAccountTypeEnumIsConsistent() {
        assertTrue(EXPECTED_VALUES.equals(Arrays.asList(EnumTest
                .getNames(BankAccountType.class))));
    }
}