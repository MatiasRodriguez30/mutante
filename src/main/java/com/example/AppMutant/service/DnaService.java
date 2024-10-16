package com.example.AppMutant.service;

import com.example.AppMutant.model.Dna;
import com.example.AppMutant.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaService {
    @Autowired
    private DnaRepository dnaRepository;

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Verificar horizontalmente
        for (String row : dna) {
            count += countSequences(row);
        }

        // Verificar verticalmente
        for (int col = 0; col < n; col++) {
            StringBuilder column = new StringBuilder();
            for (String row : dna) {
                column.append(row.charAt(col));
            }
            count += countSequences(column.toString());
        }

        // Verificar diagonalmente
        for (int i = 0; i < n; i++) {
            StringBuilder diagonal1 = new StringBuilder();
            StringBuilder diagonal2 = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (i + j < n) {
                    diagonal1.append(dna[i + j].charAt(j));
                }
                if (i - j >= 0) {
                    diagonal2.append(dna[i - j].charAt(j));
                }
            }
            count += countSequences(diagonal1.toString());
            count += countSequences(diagonal2.toString());
        }

        return count > 1;
    }

    private int countSequences(String sequence) {
        int count = 0;
        for (int i = 0; i <= sequence.length() - 4; i++) {
            if (sequence.charAt(i) == sequence.charAt(i + 1) &&
                    sequence.charAt(i) == sequence.charAt(i + 2) &&
                    sequence.charAt(i) == sequence.charAt(i + 3)) {
                count++;
            }
        }
        return count;
    }


    public void saveDna(String sequence, boolean isMutant){
        Dna dna = new Dna();
        dna.setSecuencia(sequence);
        dna.setMutant(isMutant);
        dnaRepository.save(dna);
    }
    public long countMutantDna() {
        return dnaRepository.countByIsMutant(true);
    }

    public long countHumanDna() {
        return dnaRepository.countByIsMutant(false);
    }

}
