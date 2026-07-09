# Java, for a Python Brain

*A beginner course for someone who already knows how to program.*

You're not learning to program from scratch — you already have that. Loops, variables, functions, `if` statements: you know what they *do*. This course is about learning a new **accent** for ideas you already have in your head.

Here's the honest pitch: Java is **chattier** and **stricter** than Python. Where Python lets you scribble, Java makes you fill in a form. That sounds annoying, and for the first hour it is. But that strictness is a feature — the language catches a whole category of your mistakes *before your program even runs*. By the end of this you'll see why half the software in the world (Android apps, banking systems, big game servers like Minecraft) runs on it.

Work through it at the keyboard. Reading about Java teaches you nothing; typing it in and watching it break teaches you everything.

---

## Module 0 — Set up your workshop

You need two things: a **JDK** (the thing that turns your Java into a running program) and an **editor** to write in.

> **Python comparison:** In Python you installed *one* thing, `python`, that both reads and runs your code. Java splits that into two jobs — a compiler and a runtime — and the JDK gives you both. You'll see why in Module 1.

**1. Pick an editor.**
- **Best for learning:** [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) — free, and it holds your hand in the nicest way (autocomplete, red squiggles under mistakes, one-click run), also installed JDK for you.
- **At schools:** teachers may standardise on something like BlueJ or NetBeans. That's fine — the *language* is identical, only the buttons move around.

---

## Module 1 — Hello, and what "compiled" actually means

Every Java tutorial starts here, and for once the boring example is the important one. Make a file called `Hello.java` and type this exactly:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

In Python that whole thing is one line: `print("Hello, world!")`. So what's all the extra ceremony?

- `public class Hello` — In Java, **all code lives inside a class.** There are no loose, floating statements like a Python script has. The file is called `Hello.java` and the class is called `Hello` — those *must* match.
- `public static void main(String[] args)` — This is the **front door.** When you run a Java program, it looks for this exact line and starts there. For now, just type it as a magic spell; it'll make sense in Module 6. (Roughly: `void` = "returns nothing", `String[] args` = "any words typed after the program's name".)
- `System.out.println(...)` — this is `print`. `println` = "print line" (adds a newline). There's also `System.out.print` with no newline.
- **Semicolons.** Every statement ends in `;`. Miss one and Java refuses to run. Python used the end of the line; Java uses the semicolon.
- **Curly braces `{ }`** mark where blocks begin and end. Python used indentation *and meant it*; in Java the indentation is just for your eyes — the braces are what actually matter.

### Now the interesting bit: run it two ways.

**The manual way (do this once, to see what's happening):** open a terminal in the folder and run:

```
javac Hello.java
```

Nothing prints — but look, a new file appeared: `Hello.class`. Then run:

```
java Hello
```

*Now* it prints. You just did the two steps Java always does:

1. **Compile** (`javac`): translate your human-readable `.java` into `.class` **bytecode** — a compact language the machine understands.
2. **Run** (`java`): the **JVM** (Java Virtual Machine) executes that bytecode.

> **This is the big Python difference.** Python is *interpreted*: the interpreter reads your source and runs it line by line, right now. Java is *compiled*: it translates the whole file up front, and only then runs it.
>
> Why bother? Two payoffs. First, the compiler reads your *entire* program before running anything, so it catches loads of mistakes — misspelled names, wrong types, missing pieces — and tells you at compile time instead of blowing up halfway through. Second, "write once, run anywhere": that `.class` bytecode runs on any machine that has a JVM, Windows or Mac or a phone, without changing it.

After you've seen the two steps once, let IntelliJ do them for you — just hit the green ▶ button and it compiles *and* runs in one go.

---

## Module 2 — Variables and types (the real mindset shift)

This is the lesson that actually makes Java feel different. In Python:

```python
x = 5
x = "hello"   # totally fine, x is now a string
```

In Java, every variable has a **type** that you declare, and it's **locked in**:

```java
int x = 5;
x = "hello";   // ERROR — the compiler stops you cold
```

You have to say *what kind of thing* a variable holds. The common ones:

| Type | Holds | Example |
|------|-------|---------|
| `int` | whole numbers | `int score = 42;` |
| `double` | decimal numbers | `double price = 9.99;` |
| `boolean` | true / false | `boolean isReady = true;` |
| `char` | a single character | `char grade = 'A';` |
| `String` | text | `String name = "Sam";` |

Two things trip up Python people:
- `char` uses **single** quotes `'A'`, `String` uses **double** quotes `"A"`. They're different types! In Python a quote is a quote; in Java it matters.
- `int` vs `double`: `7 / 2` gives `3` in Java (integer division, because both sides are `int`), but `7.0 / 2` gives `3.5`. The types drive the result.

> **Why the strictness is secretly good:** because the type is fixed, the compiler can catch "you're trying to do maths on a word" before you ever run the program. A huge fraction of the bugs that bite you in Python at runtime simply can't compile in Java.

**Comfort bridge:** Java has a `var` keyword that figures out the type for you from the value:

```java
var score = 42;        // Java knows this is an int
var name = "Sam";      // Java knows this is a String
```

This feels a little more like Python. But note: it's still fully typed and still locked — `var` just saves you writing the type, it doesn't make it flexible. Use it once you're comfortable; write the types out explicitly while you're learning, so you build the habit.

**Try it:** declare an `int`, a `double`, and a `String`, and `println` all three. Then try assigning a word to your `int` and watch the red squiggle appear.

---

## Module 3 — Control flow (familiar ideas, new punctuation)

You already know these cold. Here they are in Java clothes.

**if / else** — same logic, add braces, and conditions go in `( )`:

```java
if (score >= 50) {
    System.out.println("Pass");
} else if (score >= 40) {
    System.out.println("Supplementary");
} else {
    System.out.println("Fail");
}
```

Note: `and` `or` `not` from Python become `&&` `||` `!` in Java. And equality is `==`, same as Python — **except for Strings**, where you use `.equals()` (a Java gotcha we'll flag again later).

**The C-style for loop** — this one looks alien at first:

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);   // prints 0 1 2 3 4
}
```

Read it as three parts separated by semicolons: *start* (`int i = 0`), *keep going while* (`i < 5`), *do after each pass* (`i++`, which means "add 1 to i"). It's just Python's `for i in range(5)` written the long way.

**The for-each loop** — this one *will* feel like home:

```java
String[] pets = {"cat", "dog", "fish"};
for (String pet : pets) {
    System.out.println(pet);
}
```

Read the `:` as Python's `in`. That's `for pet in pets:`.

**while** is basically identical:

```java
while (lives > 0) {
    // ...
}
```

---

## Module 4 — Collections (lists and dicts, Java-style)

Python gave you `list` and `dict` and they did everything. Java splits things up more.

**Arrays** — fixed size, single type. You decide how big up front and can't grow them:

```java
int[] scores = {90, 85, 77};
System.out.println(scores[0]);      // 90
System.out.println(scores.length);  // 3  (note: no brackets, it's not a method)
```

**ArrayList** — the flexible, growable list you actually want most of the time:

```java
import java.util.ArrayList;

ArrayList<String> names = new ArrayList<>();
names.add("Sam");
names.add("Alex");
System.out.println(names.get(0));   // Sam
System.out.println(names.size());   // 2
```

The `<String>` in angle brackets says "this is a list *of Strings*." Java wants to know what's inside — you can't have a list that mixes Strings and ints like Python lets you.

**HashMap** — this is your Python `dict`:

```java
import java.util.HashMap;

HashMap<String, Integer> ages = new HashMap<>();
ages.put("Sam", 15);
ages.put("Alex", 16);
System.out.println(ages.get("Sam"));   // 15
```

`<String, Integer>` = "keys are Strings, values are ints." (Small quirk: inside these angle brackets you write `Integer`, not `int` — the capitalised "object" version. Don't overthink it yet.)

> **The pattern:** Python had one do-everything type; Java asks you to pick the right container and say what goes in it. More typing, but the compiler catches "you put a String where a number should go."

---

## Module 5 — Methods (functions with name tags)

A Python function:

```python
def add(a, b):
    return a + b
```

The Java version — you declare the **return type** and the **type of each parameter**:

```java
public static int add(int a, int b) {
    return a + b;
}
```

Breaking that down:
- `int` (the first one) = "this method hands back an `int`."
- `int a, int b` = the two inputs, each typed.
- `public static` = for now, just put it on every method you write at this stage. (`static` = "belongs to the class itself, not to a particular object" — clearer after Module 6.)

If a method hands nothing back, its return type is `void` — that's what you saw on `main`.

**Try it:** write an `add` method and a `greet(String name)` method that returns `"Hi, " + name`, and call them from `main`. Yes, `+` glues Strings together just like Python.

---

## Module 6 — Classes and objects (Java's home turf)

Here's where Java stops feeling like awkward Python and starts feeling like *itself*. This is also exactly the stuff your IT class cares most about.

A class is a **blueprint**. Here's one for a `Player`:

```java
public class Player {
    // fields — the data each player has
    String name;
    int health;

    // constructor — runs when you make a new Player
    public Player(String name, int startingHealth) {
        this.name = name;
        this.health = startingHealth;
    }

    // a method — something a player can do
    public void takeDamage(int amount) {
        this.health = this.health - amount;
        System.out.println(this.name + " now has " + this.health + " HP");
    }
}
```

Now use it from another file's `main`:

```java
Player hero = new Player("Aria", 100);
hero.takeDamage(30);   // Aria now has 70 HP
hero.takeDamage(20);   // Aria now has 50 HP
```

Compare to Python:

```python
class Player:
    def __init__(self, name, starting_health):
        self.name = name
        self.health = starting_health
```

The ideas are **identical**:
- Java's `this` is Python's `self` — "this particular object."
- The **constructor** is a method named the same as the class — it's Python's `__init__`.
- `new Player(...)` creates an object — Python did this with just `Player(...)`; Java uses the `new` keyword.

The difference is that in Java, classes aren't optional decoration — they're the *natural* way to organise a program, and your `public static void main` has been sitting inside one the whole time. That mysterious spell from Module 1 is just: "the front door lives inside a class, and `main` is where we start."

---

## Module 7 — Build a game: Guess the Number 🎲

Time to put it together. This is a complete, runnable program. Type it in (don't copy-paste — typing it is how it sinks in), run it, then break it and fix it.

```java
import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int secret = random.nextInt(100) + 1;   // 1 to 100
        int guesses = 0;
        boolean won = false;

        System.out.println("I'm thinking of a number between 1 and 100.");

        while (!won) {
            System.out.print("Your guess: ");
            int guess = input.nextInt();
            guesses = guesses + 1;

            if (guess < secret) {
                System.out.println("Too low!");
            } else if (guess > secret) {
                System.out.println("Too high!");
            } else {
                won = true;
                System.out.println("Got it in " + guesses + " guesses!");
            }
        }

        input.close();
    }
}
```

What's new here:
- **`Scanner`** is how Java reads keyboard input — it's Python's `input()`, but you build a `Scanner` object once and then call `input.nextInt()` for a number or `input.nextLine()` for text.
- **`Random`** with `random.nextInt(100)` gives a number from 0 to 99, so `+ 1` shifts it to 1–100.
- **`!won`** means "not won" — the loop keeps going *while not won*.

### Level up (try these yourself)

1. **Guess limit:** give the player only 7 guesses, then reveal the answer if they run out. (Hint: change the `while` condition.)
2. **Play again:** wrap the whole game in an outer loop that asks "Play again? (y/n)" at the end. (Hint: `input.nextLine()` and `.equals("y")`.)
3. **Rock–Paper–Scissors:** a fresh program — read the player's choice, have `Random` pick the computer's, and decide the winner. Great practice for `if`/`else` and String `.equals()`.
4. **Turn `Player` into a game:** use the `Player` class from Module 6, make two players, and let them take turns dealing damage until one hits 0 HP.

---

## The Python → Java phrasebook (keep this handy)

| You want to… | Python | Java |
|---|---|---|
| Print a line | `print(x)` | `System.out.println(x);` |
| Make a variable | `x = 5` | `int x = 5;` |
| Get input | `input()` | `new Scanner(System.in).nextLine()` |
| List | `[1, 2, 3]` | `new ArrayList<>()` or `int[]` |
| Dictionary | `{"a": 1}` | `HashMap<String, Integer>` |
| Loop N times | `for i in range(n):` | `for (int i = 0; i < n; i++) {` |
| Loop over items | `for x in items:` | `for (String x : items) {` |
| Function | `def f(a):` | `public static int f(int a) {` |
| `self` | `self` | `this` |
| `__init__` | `def __init__(self):` | a method named like the class |
| `and` / `or` / `not` | `and` `or` `not` | `&&` `\|\|` `!` |
| Compare text | `a == b` | `a.equals(b)` |
| End of a statement | *(newline)* | `;` |
| Block | *(indentation)* | `{ }` |

---

## The three things that'll trip you up (and that's normal)

1. **Forgetting semicolons.** You will do this constantly for a week, then never again. The IDE's red squiggle is your friend.
2. **`==` vs `.equals()` for text.** For numbers, `==` is fine. For Strings, always `.equals()`. Using `==` on Strings sometimes *looks* like it works and then mysteriously doesn't — it's comparing whether they're the *same object*, not the same text.
3. **Type mismatches.** "Incompatible types: String cannot be converted to int" just means you tried to put the wrong shape in a box. Read what type it *wanted* and what you *gave* it.

None of these mean you're bad at this. They're the toll you pay for a language that catches your mistakes early. Push through the first couple of hours and the strictness flips from "annoying" to "oh, it's got my back."

Have fun. Break things on purpose — it's the fastest way to learn what the rules actually are.
