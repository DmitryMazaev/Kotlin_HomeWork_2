class Actions {
    /*
    Оставил создание экземпляра класса Person, поскольку в методе show() в случае,
    когда ни один пользователь не был введен, осуществляется проверка if (person.name == null)
     */
    val person = Person(null, null, null)
    var stringFromConsole: String = ""
    var start: Boolean = true
    fun enterInConsole(): String {
        stringFromConsole = readLine().toString()
        return stringFromConsole
    }
    fun readCommand() {
        print("Введите действие: 1. exit 2. help 3. add <Имя> phone <Номер телефона> 4. add <Имя> email <Адрес электронной почты>: 5. show: ")
        val a: String = enterInConsole()
        val del = " "
        val command = a.split(del)

        if (command[0] == "exit") {
            val exit = Command.Exit()
            exit.isValid()
            newCommand(exit)
            start = false
        }
        if (command[0]  == "help") {
            val help = Command.Help()
            help.isValid()
            newCommand(help)
        }
        if (command[0] == "add" && command[2] == "phone") {
            val addPhone = Command.AddPhone(true)
            if (checkPhone()) {
                addPhone.validValue = true
                addPhone.isValid()
                newCommand(addPhone)
                person.name = command[1]
                person.phone = command[3]
                println(person)
            }
            else {
                addPhone.validValue = false
                addPhone.isValid()
                println("Введен некорректный номер")
                help()
            }
        }
        if (command[0] == "add" && command[2] == "email") {
            val addEmail = Command.AddEmail(true)
            if (checkEmail()) {
                addEmail.validValue = true
                addEmail.isValid()
                newCommand(addEmail)
                person.name = command[1]
                person.email = command[3]
                println(person)
            }
            else {
                addEmail.validValue = false
                addEmail.isValid()
                println("Введена некорректная электронная почта")
                help()
            }
        }
        if (command[0]  == "show") {
            val show = Command.Show(true)
            if (person.name == null) {
                println("Not initialized")
                show.validValue = false
                show.isValid()
            }
            else {
                show.validValue = true
                show.isValid()
                newCommand(show)
            }
        }
    }
    fun checkPhone(): Boolean {
        var a = stringFromConsole.split(" ")
        if(a[3].contains("+") && a[3].length == 12) {
            return true
        }
        else {
            return false
        }
    }
    fun checkEmail(): Boolean {
        var a = stringFromConsole.split(" ")
        if(a[3].contains("@") && a[3].contains(".")) {
            return true
        }
        else {
            return false
        }
    }

    fun help() {
        println("********************************************")
        println("Чтобы выйти, введите в консоль exit \n" +
                "Чтобы получить помощь, введите в консоль help \n" +
                "Чтобы добавить нового пользователя и его номер телефона, введите в консоль add <Имя> phone <Номер телефона> \n" +
                "Чтобы добавить нового пользователя и его электронную почту, введите в консоль add <Имя> email <Адрес электронной почты> \n" +
                "Чтобы посмотреть последнего добавленного пользователя, введите show")
        println("********************************************")
    }
    fun exit() {
        println("Вы выбрали команду exit. До свидания!")

    }
    fun addPhone() {
        var a = stringFromConsole.split(" ")
        var name = a[1]
        var phone = a[3]
        println("Имя: $name, номер телефона: $phone")
    }
    fun addEmail() {
        var a = stringFromConsole.split(" ")
        var name = a[1]
        var email = a[3]
        println("Имя: $name, электронная почта: $email")
    }
    fun show() {
        println(person)
    }
    fun newCommand(command: Command) {
        when(command) {
            is Command.Exit -> exit()
            is Command.Help -> help()
            is Command.AddPhone -> addPhone()
            is Command.AddEmail -> addEmail()
            is Command.Show -> show()
        }
    }
}