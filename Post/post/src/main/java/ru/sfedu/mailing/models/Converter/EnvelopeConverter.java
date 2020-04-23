package ru.sfedu.mailing.models.Converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.mailing.models.Envelope;
import ru.sfedu.mailing.provider.DataProviderCsv;


public class EnvelopeConverter extends AbstractBeanField<Envelope> {
    /**
     *
     * @param value
     * @return
     * @throws CsvDataTypeMismatchException
     * @throws CsvRequiredFieldEmptyException
     */
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Envelope envelope = (Envelope) value;
        return String.valueOf(envelope.getId());
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
        return dataProviderCsv.getEnvelope(Long.parseLong(s));
    }
}

