package com.example.tijanabojana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tijanabojana.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    public void fillQuestionsTable() {
        Question q1 = new Question(" Образът на кой български владител е представен върху първата златна българска монета?",
                "цар Иван Асен II", "цар Иван Александър", "цар Константин II Асен", 1);
        addQuestion(q1);

        Question q2 = new Question("Кой издава първия български буквар?",
                "Иван Богоров", "Петър Берон", "Неофит Рилски", 2);
        addQuestion(q2);

        Question q3 = new Question("Кое понятие определя отношението към българите в Османската империя през XV-XVII век?",
                "автономия", "демокрация", "дискриминация", 3);
        addQuestion(q3);

        Question q4 = new Question("За творчеството на кого от българските поети е характерна темата за безсмислието на войната?",
                "Пенчо Славейков", "Димчо Дебелянов", "Атанас Далчев", 2);
        addQuestion(q4);

        Question q5 = new Question("Кой от женските образи в посочените творби олицетворява патриархалните добродетели на българката?",
                "Мария от романа 'Тютюн' на Димитър Димов", "Ралица от едноименната поема на Пенчо Славейков", "Катерина от романа 'Железният светилник' на Димитър Талев", 2);
        addQuestion(q5);

        Question q6 = new Question("Кой е първият български роман?",
                "Чифликът край границата", "Тютюн", "Под игото", 3);
        addQuestion(q6);

        Question q7 = new Question("Кой български писател въвежда в творчеството си бялата лястовица като символ на надеждата?",
                "Пейо Яворов", "Йордан Йовков", "Димитър Димов", 2);
        addQuestion(q7);

        Question q8 = new Question("На колко години е убит Христо Ботев?",
                "На 26", "На 27", "На 28", 3);
        addQuestion(q8);

        Question q9 = new Question("За изобретяването на кое от изброените има принос българинът Петър Петров?",
                "Първият ролков магнетофон", "Първият цветен телевизор", "Първият дигитален ръчен часовник", 3);
        addQuestion(q9);

        Question q10 = new Question("Как е истинското име на Елин Пелин?",
                "Димитър Иванов Стоянов", "Димитър Димов", "Йордан Стефанов Йовков", 1);
        addQuestion(q10);

        Question q11 = new Question("Кой е автора на „Две хубави очи“?",
                "Йордан Йовков", "Димчо Дебеляков", "Пейо Яворов", 3);
        addQuestion(q11);

        Question q12 = new Question("Кое от следните произведения е на Христо Ботев?",
                "Елегия", "Гераците", "Чичовци", 1);
        addQuestion(q12);

        Question q13 = new Question("Колко са цветовете на българското знаме?",
                "2", "3", "4", 2);
        addQuestion(q13);

        Question q14 = new Question("През коя година умира Софроний Врачански?",
                "1814", "1810", "1813", 3);
        addQuestion(q14);

        Question q15 = new Question("Кога е деня на независимостта на България?",
                "22 септември", "6 септември", "15 септември", 1);
        addQuestion(q15);

        Question q16 = new Question("Кога избухва Илинденско-Преображенското въстание?",
                "1908", "1903", "1904", 2);
        addQuestion(q16);

        Question q17 = new Question("На коя дата се почита денят на българската армия?",
                "1 юни", "3 март", "6 май", 3);
        addQuestion(q17);

        Question q18 = new Question("При управлението на кой български владетел България достига три морета?",
                "Борис III", "Симеон I", "Хан Крум", 2);
        addQuestion(q18);

        Question q19 = new Question("Как се казва първият български вестник?",
                "Български орел", "Цариградски вестник", "Любословие", 1);
        addQuestion(q19);

        Question q20 = new Question("Кой е първият министър-председател на България след Освобождението?",
                "Драган Цанков", "Тодор Бурмов", "Васил Друмев", 2);
        addQuestion(q20);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
