package com.loans.loans.service.impl;

import com.loans.loans.constants.LoansConstants;
import com.loans.loans.dto.LoansDto;
import com.loans.loans.entity.Loans;
import com.loans.loans.exception.LoanAlreadyExistsException;
import com.loans.loans.exception.ResouceNotFoundException;
import com.loans.loans.mapper.LoansMapper;
import com.loans.loans.repository.LoansRepository;
import com.loans.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if (loans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists for the mobile number");
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        long randomLoanNumber = 1000000000000000L + new Random().nextInt(999999999);
        loans.setLoanNumber(Long.toString(randomLoanNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return loans;
    }

    @Override
    public LoansDto fetchLoans(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResouceNotFoundException("Loan", "loan number", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoans(LoansDto loansDto) {
        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                () -> new ResouceNotFoundException("Loan", "loan number", loansDto.getMobileNumber())
        );
        loansRepository.save(LoansMapper.mapToLoans(loansDto, loans));
        return true;
    }

    @Override
    public boolean deleteLoans(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResouceNotFoundException("Loan", "loan number", mobileNumber)
        );
        loansRepository.delete(loans);
        return true;
    }
}
