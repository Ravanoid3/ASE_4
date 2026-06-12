package de.seuhd.ktfuzzer.mode.mutational

import kotlin.random.Random

/**
 * Character-level mutators from The Fuzzing Book's MutationFuzzer
 * (<https://www.fuzzingbook.org/html/MutationFuzzer.html>), plus [repeatRandomCharacter]. Each
 * function applies one small edit to an input string and returns the result.
 */
internal object Mutators {
    /** Deletes one randomly chosen character. */
    fun deleteRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val pos = random.nextInt(input.length)
        return input.removeRange(pos, pos + 1)
    }

    /** Inserts one character drawn uniformly from [alphabet] at a random position. */
    fun insertRandomCharacter(input: String, alphabet: List<Char>, random: Random): String {
        val pos = if (input.isEmpty()) 0 else random.nextInt(input.length + 1)
        val char = alphabet[random.nextInt(alphabet.size)]
        return input.substring(0, pos) + char + input.substring(pos)
    }

    /** Flips one randomly chosen low bit of one randomly chosen character. */
    fun flipRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val pos = random.nextInt(input.length)
        val bit = 1 shl random.nextInt(7)
        val char = (input[pos].code xor bit).toChar()
        return input.substring(0, pos) + char + input.substring(pos + 1)
    }

    /** Repeats one randomly chosen character a random number of times in place. */
    fun repeatRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""
        val pos = random.nextInt(input.length)
        val count = random.nextInt(1, 10)
        val char = input[pos]
        return input.substring(0, pos) + char.toString().repeat(count + 1) + input.substring(pos + 1)
    }
}
