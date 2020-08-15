package com.sin.lifesim;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public interface stream {

    FileInputStream input();

    FileOutputStream output() throws IOException;

}
