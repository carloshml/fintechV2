package com.teste.fintech.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.fintech.entity.WalletStatement;
import com.teste.fintech.service.WalletStatementService;

class WalletStatmentControllerTest {

    @Mock
    private WalletStatementService walletStatementService;

    private WalletStatmentController walletStatmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        walletStatmentController = new WalletStatmentController(walletStatementService);
    }

    @Test
    void testGetById_SuccessfulRetrieval() {
        Long walletId = 123L;
        List<WalletStatement> walletStatements = List.of(/* create some test wallet statements */);
        when(walletStatementService.findByWallet(walletId)).thenReturn(Optional.of(walletStatements));

        ResponseEntity<List<WalletStatement>> response = walletStatmentController.GetById(walletId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(walletStatements);
    }

    // Add more test cases as needed for other methods
}
