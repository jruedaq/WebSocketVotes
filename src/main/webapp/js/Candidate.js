class Candidate {

    #name;
    #color;
    #qVotes;  

    constructor(name, color) {
        this.#name = name;
        this.#color = color;
        this.#qVotes = 0;
    }

    getName() {
        return this.#name;
    }

    getColor() {
        return this.#color;
    }

    getQVotes() {
        return parseInt(this.#qVotes);
    }

    setQVotes(value) {
        this.#qVotes = value;
    }

    addQVotes() {
        this.#qVotes++;
    }
}

