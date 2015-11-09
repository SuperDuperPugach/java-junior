package com.acme.edu.print;

import com.acme.edu.except.BufferPrinterException;

import java.io.*;

/**
 * Реализация интерфейса BufferPrinter с выводом в файл
 */
public class FilePrinter implements BufferPrinter {
    private File fileName;
    private String charSet;
    private BufferedWriter toFile;

    /**
     * Конструктор, принимающий в качетсве параметра имя файла, в который
     * необходимо писать логи
     * @param name - имя файла
     */
    public FilePrinter(String name) throws BufferPrinterException {
        this.fileName = new File(name);
        this.charSet = "UTF-8";
        initPrintWriter();
    }

    /**
     * Конструктор, принимющий в качестве параметров имя фала, в который
     * необходимо писать логи, и имя кодировки, в который записывать файл
     * @param name - имя файла
     * @param charSet - имя кодировки
     */
    public FilePrinter(String name, String charSet) throws BufferPrinterException {
        this.fileName = new File(name);
        this.charSet = charSet;
        initPrintWriter();
    }

    /**
     * Пишет передаваемое сообщение в файл
     * @param buffer - что печатать
     * @param format - шаблон вывода
     */
    @Override
    public void print(String buffer, String format) throws BufferPrinterException{
        try {
            if (toFile != null) {
                toFile.write(String.format(format, buffer) + System.lineSeparator());
            }
        } catch (IOException e) {
            new BufferPrinterException("Can't write to file (FilePrinter)");
        }
    }

    /**
     * Метод, который закрывает поток записи в файл. Необходимо
     * вызвать после окончания вывода логов
     */
    @Override
    public void close() throws BufferPrinterException {
        try {
            if(toFile != null) toFile.close();
            } catch (IOException e) {
            throw new BufferPrinterException("Can't close file(FilePrinter)");
        }
    }

    private void initPrintWriter() throws BufferPrinterException {

        try {
            toFile = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fileName,true), charSet));
        } catch (UnsupportedEncodingException e) {
            throw new BufferPrinterException("Unsupported Encoding");
        } catch (FileNotFoundException e) {
            throw new BufferPrinterException("Wrong format file name in FilePrinter object");
        }

    }

}