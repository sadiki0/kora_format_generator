package io.github.sadiki0.kora_format_generator



fun main() {
    val formats = hashMapOf(
        "UNorm" to "8",
        "SNorm" to "8",
        "UShort" to "16",
        "SShort" to "16",
        "UInt" to "32",
        "SInt" to "32",
        "ULong" to "64",
        "SLong" to "64",
        "Half" to "16",
        "Float" to "32",
        "Double" to "64",
    )
    val layouts = hashSetOf("RGBA", "BGRA", "ARGB");

    // Allow the user to know this is for the kora side of things
    println("The following is for Kora source code: ")
    // Print the rust enum
    generateKoraEnums(formats, layouts)
}

fun insertBitCount(input: String, bit: String): String {
    var output = String();
    for (c in input) {
        output += c + bit
    }
    return output
}


fun generateKoraEnums(formats: HashMap<String, String>, layouts: Set<String>) {
    // Print the enum opening
    println("pub enum Format {")

    // Add Unknown enum
    println("\tUnknown = 0,")

    var value = 1;
    for (format in formats) {
        for (layout in layouts) {
            for (i in layout.length downTo 1) {
                // Create a new string out of the layout string
                val subString = layout.substring(0, i);

                // Add the bit number to the string
                val bitString = insertBitCount(subString, format.value)

                // Add the final type
                val format = bitString + format.key;

                // Format so it would fit in the final enum
                val enumString = "\t${format} = ${value},";

                println(enumString)

                value ++
            }
        }
    }
    // Print the enum closing
    println("}")
}


