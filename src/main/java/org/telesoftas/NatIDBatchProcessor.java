package org.telesoftas;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


@Command(name = "National ID Batch Processor", version = "1.0", mixinStandardHelpOptions = true)
public class NatIDBatchProcessor implements Runnable
{
    @Parameters(paramLabel = "<file>", defaultValue = "in.txt", description = "batch file to process")
    File file = new File("in.txt");
    @Option(names = {"-f", "--filter"}, paramLabel = "male|female")
    String filter;
    @Option(names = {"-sbd", "--sort-by-birth-date"}, paramLabel = "asc|desc")
    String sort;

    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new NatIDBatchProcessor()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try (Writer outfValid = new FileWriter("valid.txt");
             Writer outfInvalid = new FileWriter("invalid.txt");
             Scanner inf = new Scanner(file)) {
            long id = 0;
            while (inf.hasNextLine()) {
                try {
                    id = inf.nextLong();
                } catch (InputMismatchException e) {
                    outfInvalid.write(String.valueOf(id) + "\n");
                    inf.next();
                } catch (NoSuchElementException e) {
                    continue;
                }
                if (NatIDValidator.validateID(id)) {
                    outfValid.write(String.valueOf(id) + "\n");
                } else {
                    outfInvalid.write(String.valueOf(id) + "\n");
                }
            }
            outfValid.close();
            outfInvalid.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(file.toString() + file.exists());
        System.out.println(filter);
        System.out.println(sort);
    }
}
