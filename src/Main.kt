//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val actions : Actions = Actions()
    while (actions.start) {
        actions.readCommand()
    }
}
