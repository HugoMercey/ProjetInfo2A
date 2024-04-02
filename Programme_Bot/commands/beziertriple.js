const { SlashCommandBuilder, AttachmentBuilder } = require('discord.js');
const Canvas = require('@napi-rs/canvas');

module.exports = {
	data: new SlashCommandBuilder()
		.setName('beziertriple')
		.setDescription('Trace une courbe de Bézier de degré 3 sur un canva de 50 par 50')
        .addIntegerOption(option=>
            option.setName('x0')
            .setDescription('Coordonées en x du début de la courbe')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('y0')
            .setDescription('Coordonées en y du début de la courbe')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('x1')
            .setDescription('Coordonées en x du premier point de contrôle')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('y1')
            .setDescription('Coordonées en y du premier point de contrôle')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('x2')
            .setDescription('Coordonées en x du deuxième point de contrôle')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('y2')
            .setDescription('Coordonées en y du deuxième point de contrôle')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('x3')
            .setDescription('Coordonées en x de la fin de la courbe')
            .setRequired(true))
        .addIntegerOption(option=>
            option.setName('y3')
            .setDescription('Coordonées en y de la fin de la courbe')
            .setRequired(true)),

	async execute(interaction) {
        let x0 = 10 * interaction.options.getInteger('x0');
        if(x0==0){
            x0+=10;
        }else if(x0==500){
            x0-=10;
        }
        let y0 = 10 * interaction.options.getInteger('y0');
        if(y0==0){
            y0+=10;
        }else if(y0==500){
            y0-=10;
        }
        let x1 = 10 * interaction.options.getInteger('x1');
        if(x1==0){
            x1+=10;
        }else if(x1==500){
            x1-=10;
        }
        let y1 = 10 * interaction.options.getInteger('y1');
        if(y1==0){
            y1+=10;
        }else if(y1==500){
            y1-=10;
        }
        let x2 = 10 * interaction.options.getInteger('x2');
        if(x2==0){
            x2+=10;
        }else if(x2==500){
            x2-=10;
        }
        let y2 = 10 * interaction.options.getInteger('y2');
        if(y2==0){
            y2+=10;
        }else if(y2==500){
            y2-=10;
        }
        let x3 = 10 * interaction.options.getInteger('x3');
        if(x3==0){
            x3+=10;
        }else if(x3==500){
            x3-=10;
        }
        let y3 = 10 * interaction.options.getInteger('y3');
        if(y3==0){
            y3+=10;
        }else if(y3==500){
            y3-=10;
        }
        const canvas = Canvas.createCanvas(500, 500);
		const ctx = canvas.getContext('2d');

        ctx.fillStyle = "white";
        ctx.fillRect(0, 0, 500, 500);

        ctx.beginPath();
        ctx.lineWidth = 5;
        ctx.strokeStyle = "black";
        ctx.moveTo(x0, y0);
        ctx.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        ctx.stroke();

        ctx.fillStyle = "red";
        ctx.beginPath();
        ctx.arc(x1, y1, 5, 0, 2 * Math.PI);
        ctx.arc(x2, y2, 5, 0, 2 * Math.PI);
        ctx.fill();

        const attachment = new AttachmentBuilder(await canvas.encode('png'), { name: 'profile-image.png' });

        interaction.reply({ files: [attachment] });
	},
};







