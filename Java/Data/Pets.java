package Data;

import java.sql.Date;

public abstract class Pets extends Animal{
    public Pets(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }
}