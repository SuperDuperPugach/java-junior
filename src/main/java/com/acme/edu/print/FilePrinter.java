package com.acme.edu.print;

import com.acme.edu.except.BufferPrinterException;

import java.io.*;
/**
 * Реализация абстрактного класса BufferPrinter с выводом в файл
 */
public class FilePrinter implements BufferPrinter {
    private File fileName;
    private String charSet;
    private PrintWriter printWriter;

    /**
     * Конструктор, принимающий в качетсве параметра имя файла, в который
     * необходимо писать логи
     * @param name - имя файла
     */
    public FilePrinter(String name) {
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
    public FilePrinter(String name, String charSet) {
        this.fileName = new File(name);
        this.charSet = charSet;
        initPrintWriter();
    }

    /**
     *
     * @param buffer - что печатать
     * @param format - шаблон вывода
     * @throws BufferPrinterException
     */
    @Override
    public void print(String buffer, String format) throws BufferPrinterException {
        if(printWriter != null) {
            printWriter.println(String.format(format, buffer));
        }
    }

    /**
     * Метод, который закрывает поток записи в файл. Необходимо
     * вызвать после окончания вывода логов
     */
    @Override
    public void close() {
        if(printWriter != null) {
            printWriter.close();
        }
    }

    private void initPrintWriter() {
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fileName,true), charSet)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}