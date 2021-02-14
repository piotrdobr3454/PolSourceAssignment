package com.poltest.poltest.Service;

import com.poltest.poltest.Model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class NoteController {

    @PostMapping("/create-new-note")
    public ResponseEntity<?> createNewNote(@RequestBody Note note) {
        try {
            insertNote(note);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/modify-note")
    public ResponseEntity<?> modifyNode(@RequestBody Note note) {
        try {
            modifyNote(note);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/delete-note")
    public ResponseEntity<HttpStatus> deleteNode(@RequestBody Note note) {
        try {
            deleteNote(note.getId());
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-notes")
    public ResponseEntity<ArrayList<Note>> getNodes() {
        try {
            ArrayList<Note> notes = new ArrayList<Note>();
            notes = getNotes();
            if (notes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(notes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all-notes")
    public ResponseEntity<ArrayList<Note>> getAllNodes() {
        try {
            ArrayList<Note> notes = new ArrayList<Note>();
            notes = getAllNotes();
            if (notes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(notes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-previous-notes/{id}")
    public ResponseEntity<ArrayList<Note>> getPreviousNodes(@PathVariable("id") int id) {
        ArrayList<Note> previousnotes = new ArrayList<Note>();
        try {
            previousnotes = getPreviousNotes(id);
            if (previousnotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(previousnotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-notes/{id}")
    public ResponseEntity<Note> getNodeById(@PathVariable("id") int id) {
        try {
            Note note = getNoteById(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-version/{id}")
    public ResponseEntity<String> getVersionByID(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(getVersion(id), HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String getVersion(int id) throws SQLException {
        String sql = "SELECT version FROM Notes WHERE id = ?";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        else {
            return null;
        }

    }

    public static Note getNoteById(int id) throws SQLException {
        String sql = "SELECT * FROM Notes WHERE id = ?";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            return new Note(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getInt(8));
        }
        else {
            return null;
        }
    }

    public static ArrayList<Note> getPreviousNotes(int id) throws SQLException {
        ArrayList<Note> return_records = new ArrayList<>();

        if(id == 0) {
            return return_records;
        }
        String sql = "SELECT * FROM Notes WHERE original = ? OR (id = ? AND original = 0) ORDER BY version DESC";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            return_records.add(new Note(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(8)));
        }
        return_records.remove(0);

        con.close();
        return return_records;
    }

    public static ArrayList<Note> getNotes() throws SQLException{

        ArrayList<Note> return_records = new ArrayList<>();

        String sql = "SELECT * FROM Notes WHERE visibility = true";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            return_records.add(new Note(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(8)));
        }
        con.close();
        return return_records;
    }

    public static ArrayList<Note> getAllNotes() throws SQLException{

        ArrayList<Note> return_records = new ArrayList<>();

        String sql = "SELECT * FROM Notes";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            return_records.add(new Note(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(8)));
        }
        con.close();
        return return_records;
    }

    public static void insertNote(Note note) throws SQLException {
        Date date = new Date();
        String sql = "INSERT INTO Notes (title, content, date_initial, date_modified, version, visibility, original) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, note.getTitle());
        preparedStatement.setString(2, note.getContent());
        preparedStatement.setString(3, date.toString());
        preparedStatement.setString(4, date.toString());
        preparedStatement.setString(5, "1");
        preparedStatement.setBoolean(6, true);
        preparedStatement.setInt(7, note.getId());
        preparedStatement.execute();
        con.close();
    }

    public static void modifyNote(Note note) throws SQLException {
        int previousID = note.getOriginal();
        if(previousID == 0) {
            previousID = note.getId();
        }
        String date_initial = getNoteById(previousID).getDate_initial();
        Integer version = Integer.parseInt(getVersion(note.getId()));
        version = version + 1;
        Date date = new Date();
        deleteNote(note.getId());
        String sql = "INSERT INTO Notes (title, content, date_initial, date_modified, version, visibility, original) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, note.getTitle());
        preparedStatement.setString(2, note.getContent());
        preparedStatement.setString(3, date_initial);
        preparedStatement.setString(4, date.toString());
        preparedStatement.setString(5, version.toString());
        preparedStatement.setBoolean(6, true);
        preparedStatement.setInt(7, previousID);
        preparedStatement.execute();
        con.close();
    }

    public static void deleteNote(int id) throws SQLException {
        String sql = "UPDATE Notes SET visibility = false WHERE id = ?";
        Connection con = getConnectionToSQL();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        con.close();
    }

    public static Connection getConnectionToSQL() throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/poltest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "password"
        );
        return con;
    }
}
