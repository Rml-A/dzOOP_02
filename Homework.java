public class Homework {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Задание
     *
     * Предположим, вы разрабатываете какую-то компьютерную игру, в которой есть несколько типов объектов (классов):
     * 1. Здание (имеет текущий уровень здоровья)
     * 2. Герой (имеет текущий уровень здоровья и текущий уровень магической энергии)
     * 3. Нейтральный персонаж (имеет текущий уровень здоровья)
     *
     * Также у вас есть класс Render с методом showIndicator, который срабатывает при наведении мышки на объект
     * и работает следующим образом:
     * 1. Если объект обладает уровнем здоровья, то отображается индикатор с текущим уровнем здоровья.
     * 2. Если объект обладает уровнем магический энергии, то отображается индикатор с текущим уровнем энергии.
     * * В качестве упрощения, пусть вывод на дисплей это запись информации в консоль.
     * * То есть вы пишете код, который выводит на консоль информацию.
     *
     *
     * Подсказка: нужно ввести 2 интерфейса: наличие здоровья и наличие магической энергии.
     * В классе Render проверять только на эти интерфейсы и выводить нужную информацию.
     * Необходимо продумать, какие методы должны быть в интерфейсе.
     *
     * ЧТО ДЕЛАТЬ НЕ НУЖНО:
     * Нельзя завязываться на конкретные классы. Предполагается, что таких классов очень много и они постоянно добавляются.
     *
     * * Необязательные задания, которые не относятся к теме, но при сильном желании можно реализовать.
     * * Со звездочкой: реализовать в консоли отображение индикатора.
     * Например, при максимальном уровне здоровья 100 и текущем 40 можно отобразить вот такую ленточку: [xxxx      ]
     * ** С двумя звездочками: раскрасить вывод. Чем меньше здоровья, тем "краснее" цвет индикатора. Для полного здоровья - цвет зеленый.
     */

    public static void main(String[] args) {
        Render render = new Render();

        Building building = new Building(100);
        building.setCurrentHealthPoint(50);
        System.out.println(building.getClass());
        render.showIndicator(building); // В консоли должно быть примерно так: Текущий уровень здоровья: 50, максимальный уровень здоровья: 100

        Hero hero = new Hero(100,100);
        hero.setCurrentHealthPoint(70);
        hero.setCurrentManaPoint(30);
        System.out.println(hero.getClass());
        render.showIndicator(hero);

        Neutral neutral = new Neutral(100);
        neutral.setCurrentHealthPoint(75);
        System.out.println(neutral.getClass());
        render.showIndicator(neutral);

        //System.out.println(ANSI_RED + "This text has a red background but default text!" + ANSI_RESET);
        //System.out.println(ANSI_GREEN + "This text has a green background but default text!" + ANSI_RESET);

    }

    static class Render {

        /**
         * Если объект обладает уровнем здоровья, то отображается индикатор с текущим уровнем здоровья.
         * Если объект обладает уровнем магический энергии, то отображается индикатор с текущим уровнем энергии.
         * * В качестве упрощения, пусть вывод на дисплей = запись информации в консоль.
         */
        public void showIndicator(Object object) {
            // Не должно быть упоминания конкретных классов!!!
            if (object instanceof HasHealth){
                int maxHealth = ((HasHealth)object).getMaxHealthPoint();
                int currentHealth = ((HasHealth)object).getCurrentHealthPoint();
                int levelHealth = (currentHealth*10)/maxHealth;
                String str = "";
                String str1 = "";
                for (int i=0; i<levelHealth; i++){
                    str+="x";
                }
                for (int i=levelHealth; i<10; i++){
                    str1+=" ";
                }
                System.out.println("level of Health: ["+str+str1+"]");
            }

            if (object instanceof HasMana){
                int maxMana = ((HasMana)object).getMaxManaPoint();
                int currentMana = ((HasMana)object).getCurrentManaPoint();
                int levelMana = (currentMana*10)/maxMana;
                String str = "";
                String str1 = "";
                for (int i=0; i<levelMana; i++){
                    str+="x";
                }
                for (int i=levelMana; i<10; i++){
                    str1+=" ";
                }
                System.out.println("level of Mana: ["+str+str1+"]");
            }
        }


    }

    interface HasHealth{
        int getMaxHealthPoint();
        int getCurrentHealthPoint();
    }

    interface HasMana{
        int getMaxManaPoint();
        int getCurrentManaPoint();
    }


    static class Building implements HasHealth{

        private int maxHealthPoint; // максимально количество здоровья
        private int currentHealthPoint; // текущее количество здоровья

        public Building(int maxHealthPoint) {
            this.maxHealthPoint = maxHealthPoint;
            this.currentHealthPoint = maxHealthPoint;
        }

        public void setCurrentHealthPoint(int currentHealthPoint) {
            this.currentHealthPoint = currentHealthPoint;
        }

        public int getCurrentHealthPoint(){
            return this.currentHealthPoint;
        }

        public int getMaxHealthPoint(){
            return this.maxHealthPoint;
        }

        // FIXME: 29.06.2023 Дописать нужное
    }

    static class Hero implements HasHealth, HasMana {

        private int maxHealthPoint; // максимально количество здоровья
        private int currentHealthPoint; // текущее количество здоровья

        private int maxManaPoint; // максимально количество магический энергии
        private int currentManaPoint; // текущее количество магический энергии

        public Hero(int maxHealthPoint, int maxManaPoint) {
            this.maxHealthPoint = maxHealthPoint;
            this.maxManaPoint = maxManaPoint;

            this.currentHealthPoint = maxHealthPoint;
            this.currentManaPoint = maxManaPoint;
        }

        public void setCurrentHealthPoint(int currentHealthPoint) {
            this.currentHealthPoint = currentHealthPoint;
        }

        public void setCurrentManaPoint(int currentManaPoint) {
            this.currentManaPoint = currentManaPoint;
        }

        public int getCurrentHealthPoint(){
            return this.currentHealthPoint;
        }

        public int getMaxHealthPoint(){
            return this.maxHealthPoint;
        }

        public int getCurrentManaPoint(){
            return this.currentManaPoint;
        }

        public int getMaxManaPoint(){
            return this.maxManaPoint;
        }

        // FIXME: 29.06.2023 Дописать нужное
    }

    static class Neutral implements HasHealth{

        private int maxHealthPoint; 
        private int currentHealthPoint; 

        public Neutral(int maxHealthPoint) {
            this.maxHealthPoint = maxHealthPoint;
            this.currentHealthPoint = maxHealthPoint;
        }

        public void setCurrentHealthPoint(int currentHealthPoint) {
            this.currentHealthPoint = currentHealthPoint;
        }

        public int getCurrentHealthPoint(){
            return this.currentHealthPoint;
        }

        public int getMaxHealthPoint(){
            return this.maxHealthPoint;
        }

        
    }
    
}