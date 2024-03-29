package com.example.coursePrj.services;

import com.example.coursePrj.models.GenderTrain;
import com.example.coursePrj.models.TrMccCodes;
import com.example.coursePrj.models.TrTypes;
import com.example.coursePrj.models.Transactions;
import com.example.coursePrj.repositories.GenderTrainRepository;
import com.example.coursePrj.repositories.TrMccCodesRepository;
import com.example.coursePrj.repositories.TrTypesRepository;
import com.example.coursePrj.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private GenderTrainRepository genderTrainRepository;
    @Autowired
    private TrMccCodesRepository trMccCodesRepository;
    @Autowired
    private TrTypesRepository trTypesRepository;

    public void upload(MultipartFile file) throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<List<String>> list = new ArrayList<>();

        for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {

            List<String> subList = new ArrayList<>();
            subList = List.of(line.split(";"));
            list.add(subList);
        }

        if ("transactions.csv".equals(file.getOriginalFilename())) {
            transactionsRepository.saveAll(saveToTransactions(list));
        }

        else if ("gender_train.csv".equals(file.getOriginalFilename())) {
            genderTrainRepository.saveAll(saveToGenderTrain(list));
        }

        else if ("tr_mcc_codes.csv".equals(file.getOriginalFilename())) {
            trMccCodesRepository.saveAll(saveToTrMccCodes(list));
        }
        else if ("tr_types.csv".equals(file.getOriginalFilename())) {
            trTypesRepository.saveAll(saveToTrTypes(list));
        }
        else {
            throw new Exception();
        }
    }

    private List<Transactions> saveToTransactions(List<List<String>> list) {
        List<Transactions> transactions = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            Long customer_id = Long.parseLong(list.get(i).get(0));
            String tr_datetime = list.get(i).get(1);
            int mcc_code = Integer.parseInt(list.get(i).get(2));
            int tr_type = Integer.parseInt(list.get(i).get(3));
            int amount = Integer.parseInt(list.get(i).get(4));
            int term_id = Integer.parseInt(list.get(i).get(5));

            Transactions transaction = new Transactions(customer_id, tr_datetime, mcc_code, tr_type, amount, term_id);
            transactions.add(transaction);
        }

        return transactions;
    }

    private List<GenderTrain> saveToGenderTrain(List<List<String>> list) {
        List<GenderTrain> genderTrains = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            Long customer_id = Long.parseLong(list.get(i).get(0));
            int gender = Integer.parseInt(list.get(i).get(1));

            GenderTrain genderTrain = new GenderTrain(customer_id, gender);
            genderTrains.add(genderTrain);
        }

        return genderTrains;
    }

    private List<TrMccCodes> saveToTrMccCodes(List<List<String>> list) {
        List<TrMccCodes> trMccCodes = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            Long mcc_code = Long.parseLong(list.get(i).get(0));
            String mcc_description = list.get(i).get(1);

            TrMccCodes trMccCode = new TrMccCodes(mcc_code, mcc_description);
            trMccCodes.add(trMccCode);
        }

        return trMccCodes;
    }

    private List<TrTypes> saveToTrTypes(List<List<String>> list) {
        List<TrTypes> trTypes = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            Long tr_type = Long.parseLong(list.get(i).get(0));
            String tr_description = list.get(i).get(1);

            TrTypes trType = new TrTypes(tr_type, tr_description);
            trTypes.add(trType);
        }

        return trTypes;
    }
}
