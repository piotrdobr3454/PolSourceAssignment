package com.poltest.poltest.Service;

import com.poltest.poltest.Model.Note;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@RestController
public class NoteController {

    @PostMapping("/create-new-note")
    public boolean createNewNote(@RequestBody Note note) {
        try {
            if(insertNote(note)) {
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @PostMapping("/modify-note")
    public boolean modifyNode(@RequestBody Note note) {
        try {
            if(modifyNote(note)) {
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @PostMapping("/delete-note/{id}")
    public boolean deleteNode(@PathVariable int id) {
        try {
            if(deleteNote(id)) {
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean insertNote(Note note) throws SQLException {
        Date date = new Date();
        String sql = "INSERT INTO Notes (id, title, content, date_initial, date_modified) VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, note.getId());
        preparedStatement.setString(2, note.getTitle());
        preparedStatement.setString(3, note.getContent());
        preparedStatement.setString(4, date.toString());
        preparedStatement.setString(5, date.toString());
        preparedStatement.execute();
        con.close();
        return true;

    }

    public static boolean modifyNote(Note note) throws SQLException {
        Date date = new Date();
        String sql = "UPDATE Notes SET title = ?, content = ?, date_modified = ? WHERE id = ?";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, note.getTitle());
        preparedStatement.setString(2, note.getContent());
        preparedStatement.setString(3, date.toString());
        preparedStatement.setInt(4, note.getId());
        preparedStatement.execute();
        con.close();
        return true;
    }

    public static boolean deleteNote(int id) throws SQLException {
        String sql = "DELETE FROM Notes WHERE id = ?";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        con.close();
        return true;
    }

    public static Connection getConnectionToSQL() throws SQLException
    {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/poltest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "password"
        );
        return con;
    }
}
