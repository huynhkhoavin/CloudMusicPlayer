package technologies.pa.cloudmediaplayer.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import technologies.pa.cloudmediaplayer.Folder.CurrentFile;
import technologies.pa.cloudmediaplayer.Folder.File;
import technologies.pa.cloudmediaplayer.Folder.Folder;
import technologies.pa.cloudmediaplayer.Object.Song;

/**
 * Created by Dev02 on 3/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "music.db";

    public static final int DATABASE_VERSION = 1;

    private static final String TAG = "DatabaseHelper";

    //region SONG_TABLE
    public static final String SONG_TABLE = "Song";
    private static final String SONG_ID = "id";
    private static final String SONG_FOLDER_ID = "folder_id";
    private static final String SONG_TITLE = "title";
    private static final String SONG_SINGER = "singer";
    private static final String SONG_PATH = "path";
    //endregion
    //region PLAYLIST_TABLE
    private static final String PLAYLIST_TABLE = "Playlist";
    private static final String PLAYLIST_ID = "id";
    private static final String PLAYLIST_NAME = "name";
    //endregion
    //region PLAYLIST_DETAIL_TABLE
    private static final String PLAYLIST_DETAIL_TABLE = "PlaylistDetail";
    private static final String PD_PLAYLIST_ID = "playlist_id";
    private static final String PD_SONG_ID = "song_id";
    //endregion
    //region FOLDER
    private static final String FOLDER_TABLE = "Folder";
    private static final String FOLDER_ID = "id";
    private static final String FOLDER_TITLE = "title";
    private static final String FOLDER_PATH = "path";
    //endregion
    //region FILE
    private static final String FILE_TABLE = "File";
    private static final String FILE_ID = "id";
    private static final String FILE_FOLDER_ID = "folder_id";
    private static final String FILE_TITLE = "title";
    private static final String FILE_PATH  = "path";
    //endregion
    //region CURRENT_PLAYING_PLAYLIST
    private static final String CURRENT_FOLDER_TABLE = "CurrentFolder";
    private static final String CURRENT_FOLDER_ID = "id";
    private static final String CURRENT_POSITION_FILE = "currentPos";
    //endregion

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       // db=SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        db.execSQL(createSong());
        db.execSQL(createPlaylist());
        db.execSQL(createPlaylistDetail());
        db.execSQL(createFolder());
        db.execSQL(createFile());
        db.execSQL(createCurrentPlaylist());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addSong(Song song, int folderId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SONG_FOLDER_ID,folderId);
        values.put(SONG_TITLE,song.getSongTitle());
        values.put(SONG_SINGER,song.getSingerName());
        values.put(SONG_PATH,song.getPath());
        db.insert(SONG_TABLE,null,values);
        db.close();
    }
    //region Create
    public String createSong(){
        return "CREATE TABLE " + SONG_TABLE + "("
                + SONG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SONG_TITLE + " TEXT, "
                + SONG_PATH + " TEXT, "
                + SONG_SINGER +" TEXT"+
                ")";
    }
    public String createPlaylist(){
        return  "CREATE TABLE "+PLAYLIST_TABLE+" (" +
                PLAYLIST_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PLAYLIST_NAME+" TEXT" +
                ")";
//        return "CREATE TABLE" + PLAYLIST_TABLE + "("+SONG_ID + " INTEGER PRIMARY KEY,"+ PLAYLIST_NAME + " TEXT"+")";
    }
    public String createPlaylistDetail(){
        return  "CREATE TABLE "+PLAYLIST_DETAIL_TABLE+" (" +
                PD_PLAYLIST_ID+" INTEGER," +
                PD_SONG_ID+" INTEGER," +
                "PRIMARY KEY("+PD_PLAYLIST_ID+","+PD_SONG_ID+")" +
                ")";
//        return "CREATE TABLE"+"("+PD_PLAYLIST_ID+" INTEGER PRIMARY KEY,"+PD_SONG_ID+" INTEGER PRIMARY KEY"+")";
    }
    public String createFolder(){
        return "CREATE TABLE "+FOLDER_TABLE+" ("+
                FOLDER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FOLDER_TITLE+" TEXT, "+
                FOLDER_PATH+" TEXT"+
                ")";
    }
    public String createFile(){
        return "CREATE TABLE "+FILE_TABLE+" ("+
                FILE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FILE_FOLDER_ID+ " INTEGER,"+
                FILE_TITLE+" TEXT, "+
                FILE_PATH+" TEXT "+
                ")";
    }
    public String createCurrentPlaylist(){
        return "CREATE TABLE "+CURRENT_FOLDER_TABLE+" ("+
                CURRENT_FOLDER_ID +" INTEGER PRIMARY KEY, "+
                CURRENT_POSITION_FILE+" INTEGER"
                +" )";
    }
    //endregion

    //region Select
    public void UpdateCurrentPlayingFolder(int newFolderId, int currentPosFile){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("delete from " + CURRENT_FOLDER_TABLE);
        }catch (Exception e){
            Log.e(TAG, "Error occur when clear table CurrentPlay on DB");
        }
        ContentValues cv = new ContentValues();
        cv.put(CURRENT_FOLDER_ID,newFolderId);
        cv.put(CURRENT_POSITION_FILE,currentPosFile);
        db.insert(CURRENT_FOLDER_TABLE,null,cv);
    }
    public CurrentFile getCurrentFile(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+CURRENT_FOLDER_TABLE,null);
        CurrentFile currentFile = new CurrentFile();
        if (cursor.moveToFirst()) // data?

            currentFile.setFolderId(cursor.getInt(cursor.getColumnIndex(CURRENT_FOLDER_ID)));
            currentFile.setFilePos(cursor.getInt(cursor.getColumnIndex(CURRENT_POSITION_FILE)));
        cursor.close();
        return currentFile;
    }
    public ArrayList<File> getCurrentPlayingFileList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<File> listFileFromFolder = new ArrayList<>();
        int FolderId = -1;
        Cursor cursor = db.rawQuery("select * from "+CURRENT_FOLDER_TABLE,null);
        if (cursor.moveToFirst()){
            do{
                FolderId = cursor.getInt(cursor.getColumnIndex(CURRENT_FOLDER_ID));
            }while(cursor.moveToNext());
        }
        if(FolderId!=-1){
                listFileFromFolder = getAllFileFromFolder(FolderId);
            }
        else
            {
                listFileFromFolder = getAllFile();
            }
        return listFileFromFolder;
    }
    public ArrayList<File> getAllFile(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<File> fileArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+FILE_TABLE,null);
        if (cursor.moveToFirst()){
            do{
                int Id = cursor.getInt(cursor.getColumnIndex(FILE_ID));
                int folderId = cursor.getInt(cursor.getColumnIndex(FILE_FOLDER_ID));
                String FileTitle = cursor.getString(cursor.getColumnIndex(FILE_TITLE));
                String FilePath = cursor.getString(cursor.getColumnIndex(FILE_PATH));
                File fi = new File(folderId,Id,FilePath,FileTitle);
                fileArrayList.add(fi);
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        return fileArrayList;
    }
    public ArrayList<File> getAllFileFromFolder(int FolderId){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<File> fileArrayList = new ArrayList<>();
        String[] tableColum =  new String[]{FILE_ID,FILE_FOLDER_ID,FILE_TITLE,FILE_PATH};
        String whereClause = FILE_FOLDER_ID+"=?";
        String[] whereArg = new String[]{String.valueOf(FolderId)};
        Cursor cursor = db.query(FILE_TABLE,tableColum,whereClause,whereArg,null,null,null);
        if (cursor.moveToFirst()){
            do{
                int Id = cursor.getInt(cursor.getColumnIndex(FILE_ID));
                int folderId = cursor.getInt(cursor.getColumnIndex(FILE_FOLDER_ID));
                String FileTitle = cursor.getString(cursor.getColumnIndex(FILE_TITLE));
                String FilePath = cursor.getString(cursor.getColumnIndex(FILE_PATH));
                File fi = new File(folderId,Id,FilePath,FileTitle);
                fileArrayList.add(fi);
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
        return fileArrayList;
        // return contact
    }
    //endregion
    public void addFolder(Folder folder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOLDER_ID,folder.getId());
        values.put(FOLDER_TITLE,folder.getTitle());
        values.put(FOLDER_PATH,folder.getPath());
        db.insert(FOLDER_TABLE,null,values);
        db.close();
    }
    public void addFile(File file, int FolderId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FILE_FOLDER_ID,FolderId);
        values.put(FILE_TITLE,file.getTitle());
        values.put(FILE_PATH,file.getPath());
        db.insert(FILE_TABLE,null,values);
        db.close();
    }
    public void addListFileToFolder(ArrayList<File> fileArrayList, int FolderId){
        for (File file : fileArrayList){
            addFile(file,FolderId);
        }
    }

    public void ClearFolderTable() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL("delete from "+FOLDER_TABLE);
            db.execSQL("delete from "+FILE_TABLE);
            }
            catch (Exception e){
                Log.e("Database","Can't delete empty table");
            }
    }
    public ArrayList<Folder> getAllFolder(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Folder> listFolders = new ArrayList<>();
        String[] tableColum =  new String[]{FOLDER_ID,FOLDER_TITLE,FOLDER_PATH};
        Cursor cursor = db.rawQuery("select * from "+FOLDER_TABLE,null);
            if (cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndex(FOLDER_ID));
                    String folderTitle = cursor.getString(cursor.getColumnIndex(FOLDER_TITLE));
                    String folderPath = cursor.getString(cursor.getColumnIndex(FOLDER_PATH));
                    listFolders.add(new Folder(id,folderTitle,folderPath));
                }while(cursor.moveToNext());
            }
            cursor.close();
        return listFolders;
        // return contact
    }
}
