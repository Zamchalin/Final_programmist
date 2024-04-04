package Data;

import java.sql.Date;

public abstract class Packs extends Animal{
    public Packs(String name, Date birthday, String commands) {
        super(name, birthday, commands);
    }
}