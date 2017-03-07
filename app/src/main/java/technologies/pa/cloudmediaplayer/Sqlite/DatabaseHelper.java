package technologies.pa.cloudmediaplayer.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import technologies.pa.cloudmediaplayer.Object.Song;

/**
 * Created by Dev02 on 3/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "music.db";

    public static final int DATABASE_VERSION = 1;

    //region SONG_TABLE
    public static final String SONG_TABLE = "Song";
    private static final String SONG_ID = "id";
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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createSong());
        db.execSQL(createPlaylist());
        db.execSQL(createPlaylistDetail());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SONG_TITLE,song.getSongTitle());
        values.put(SONG_SINGER,song.getSingerName());
        values.put(SONG_PATH,song.getPath());
        db.insert(SONG_TABLE,null,values);
        db.close();
    }
    public Song getSong(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] tableColum =  new String[]{SONG_ID,SONG_TITLE,SONG_SINGER,SONG_PATH};
        String whereClause = SONG_ID+"=?";
        String[] whereArg = new String[]{String.valueOf(id)};
        Cursor cursor = db.query(SONG_TABLE,tableColum,whereClause,whereArg,null,null,null);
        Song song = null;
        if (cursor != null) {
            cursor.moveToFirst();
            int x = cursor.getInt(0);
            String title = cursor.getString(1);
            String singer = cursor.getString(2);
            String path = cursor.getString(3);
            song = new Song(title,singer,path);

        }
        return song;
        // return contact
    }
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
}
