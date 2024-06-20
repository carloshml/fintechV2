package com.teste.fintech.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.fintech.entity.WalletAction;
import com.teste.fintech.entity.WalletType;
import com.teste.fintech.repository.WalletActionRepository;
import com.teste.fintech.repository.WalletTypeRepository;

@DataJpaTest
@ActiveProfiles("test")
public class DataloaderTest {

    @Mock
    private WalletTypeRepository walletTypeRepository;

    @Mock
    private WalletActionRepository walletActionRepository;

    private Dataloader dataloader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dataloader = new Dataloader(walletTypeRepository, walletActionRepository);
    }

    @Test
    void testRun_SuccessfulExecution() throws Exception {
        // Mock: Suponha que não haja exceções durante o salvamento
    	Mockito.when(walletTypeRepository.save(any())).thenReturn(new WalletType());
      	Mockito.when(walletActionRepository.save(any())).thenReturn(new WalletAction());
        // Execute o método run
        dataloader.run();

        // Verifique se o save foi chamado para cada tipo de carteira e ação de carteira
        verify(walletTypeRepository, times(WalletType.Enum.values().length)).save(any());
        verify(walletActionRepository, times(WalletAction.Enum.values().length)).save(any());
    }

    
}