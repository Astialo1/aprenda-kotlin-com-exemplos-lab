// Representa um conteúdo educacional dentro de uma formação
data class ConteudoEducacional(
    val nome: String,
    val duracao: Int, // duração em horas
    val prerequisito: ConteudoEducacional? = null // Pode ter um pré-requisito
)

// Representa um aluno
data class Aluno(val nome: String, val email: String)

// Representa uma formação
class Formacao(
    val nome: String,
    val nivel: Nivel,
    val conteudos: List<ConteudoEducacional>,
    private val limiteAlunos: Int = 50 // Definindo um limite de alunos por formação
) {
    private val alunosMatriculados = mutableListOf<Aluno>()

    // Método para matricular alunos
    fun matricular(aluno: Aluno) {
        if (alunosMatriculados.size >= limiteAlunos) {
            println("Matrícula falhou: Limite de alunos atingido para a formação $nome.")
            return
        }
        if (alunosMatriculados.contains(aluno)) {
            println("${aluno.nome} já está matriculado na formação $nome.")
            return
        }
        alunosMatriculados.add(aluno)
        println("${aluno.nome} foi matriculado na formação $nome.")
    }

    // Método para listar alunos matriculados
    fun listarAlunos() {
        println("\nAlunos matriculados na formação $nome:")
        if (alunosMatriculados.isEmpty()) {
            println("Nenhum aluno matriculado.")
        } else {
            alunosMatriculados.forEach { println("- ${it.nome}") }
        }
    }
}

// Enum para representar os níveis das formações
enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

// Função para simular testes
fun main() {
    println("🚀 Iniciando os testes...")

    // Criando conteúdos educacionais com pré-requisitos
    val kotlinBasico = ConteudoEducacional("Introdução ao Kotlin", 8)
    val pooKotlin = ConteudoEducacional("POO em Kotlin", 12, kotlinBasico)
    val coroutines = ConteudoEducacional("Programação Assíncrona com Kotlin Coroutines", 10, pooKotlin)

    // Criando formação
    val formacaoKotlin = Formacao("Kotlin Developer", Nivel.INTERMEDIARIO, listOf(kotlinBasico, pooKotlin, coroutines), limiteAlunos = 2)

    // Criando alunos
    val aluno1 = Aluno("Bruno", "bruno@email.com")
    val aluno2 = Aluno("Mariana", "mariana@email.com")
    val aluno3 = Aluno("Carlos", "carlos@email.com") // Este aluno não conseguirá se matricular

    // Testando matrícula
    formacaoKotlin.matricular(aluno1) // OK
    formacaoKotlin.matricular(aluno2) // OK
    formacaoKotlin.matricular(aluno1) // Falha (já matriculado)
    formacaoKotlin.matricular(aluno3) // Falha (limite de alunos)

    // Listando alunos matriculados
    formacaoKotlin.listarAlunos()
}
