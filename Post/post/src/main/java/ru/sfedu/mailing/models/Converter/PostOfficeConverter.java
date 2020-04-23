package ru.sfedu.mailing.models.Converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.mailing.models.PostOffice;
import ru.sfedu.mailing.provider.DataProviderCsv;


public class PostOfficeConverter extends AbstractBeanField<PostOffice> {
    /**
     *
     * @param value
     * @return
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        PostOffice postOffice = (PostOffice) value;
        return String.valueOf(postOffice.getOfficeId());
    }

    /**
     *
     * @param s
     * @return
     * @throws CsvDataTypeMismatchException
     * @throws CsvConstraintViolationException
     */
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        DataProviderCsv dataProviderCsv = new DataProviderCsv();
        return dataProviderCsv.getPostOffice(Long.parseLong(s));
    }
}
