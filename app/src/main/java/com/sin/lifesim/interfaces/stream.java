package com.sin.lifesim.interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface stream {

    FileInputStream input() throws FileNotFoundException;

    FileOutputStream output() throws IOException;

}
