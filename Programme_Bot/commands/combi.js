const { SlashCommandBuilder } = require('discord.js');

module.exports = {
	data: new SlashCommandBuilder()
		.setName('combi')
		.setDescription('Repond avec les n premieres combinaisons')
        .addIntegerOption(option=>
            option.setName('n')
            .setDescription("null")
            .setRequired(true)),
	async execute(interaction) {
        var max = interaction.options.getInteger('n');
        var reply = "";
		for(var n=0; n<=max; n++){
            for(var k=0; k<=n; k++){
                combi = facto(n)/(facto(k)*facto((n-k)));
                reply = reply + " " + combi;
            }
            reply += "\n";
        }
        return interaction.reply(reply);
	}
};

function facto(nbr){
    var i, nbr, f = 1;
    for(i = 1; i <= nbr; i++){
      f = f * i;
    }
    return f;
};