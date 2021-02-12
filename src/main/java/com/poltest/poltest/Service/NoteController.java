package com.poltest.poltest.Service;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.poltest.poltest.Model.Note;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class NoteController {

    @GetMapping("/create-new-note")
    public boolean createNewNote(@RequestBody Note note) {
        Date date = new Date();
        note.setInitial(date);
        String sql = "kjhsk";
        PreparedStatement preparedStatement = Connection.pre
        }
    }
}
