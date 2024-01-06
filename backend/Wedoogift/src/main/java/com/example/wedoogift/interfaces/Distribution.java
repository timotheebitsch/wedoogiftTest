package com.example.wedoogift.interfaces;

import java.time.LocalDate;

public interface Distribution {
	double getAmount();
    LocalDate getDistributionDate();
    LocalDate getExpirationDate();
}
