function System() {
    this.body = $("body");
}

function Cell(name, links) {
    this.name = name;
    this.links = links;
}

function CellLink(name, url) {
    this.name = name;
    this.url = url;
    this.image = image;
}

function MainComponent(data) {
    this.data = data;
    this.number = 0;
    this.currentcell = null;
    $("body").css("background-image", "url('/images/browse-bg.jpg')").css("background-size", "cover").css("background-position", "center");
    this.registerLinks = function() {
        $("a").off().click(function() {
            if($(this).hasClass("browse")) {
                StudWorld.main.browseShow();
                return false;
            }
            if($(this).hasClass("browse-cell")) {
                StudWorld.main.forceCellURL($(this).attr("href"));
                return false;
            }
            if($(this).hasClass("slide-link")) {
                StudWorld.main.forceSlideURL($(this).attr("href"));
                return false;
            }
        });
    };
    this.browseShow = function() {
        this.currentcell = null;
        $("#main-container").fadeTo(500, 0, function() {
            $("#main-container > *").remove();
        });
        $(".interface-right").fadeTo(500, 0, function() {
            $(this).css("display","none");
        });
        var bg = $('<div></div>')
        .addClass("browse-bg");
        for(var i = 0; i < 3; i++) {
            var r = $('<div></div>')
            .addClass("browse-row");
            for(var q = 0; q<3;q++) {
                if(StudWorld.main.data[i][q]===undefined) {continue;}
                var t = $('<a></a>')
                .addClass("browse-cell")
                .addClass("cell-"+q)
                .attr("href",StudWorld.main.data[i][q].url)
                .append(
                    $('<p></p>').text(StudWorld.main.data[i][q].name)
                )
                .append(
                    $('<div></div>').attr("id", "bg").css("background-image", "url("+StudWorld.main.data[i][q].image+")").css("background-size", "cover").css("background-position", "center")
                );
                r.prepend(t);
            }
            bg.append(r);
        }
        $("body").append(bg);
        /*$(".browse-cell").hover(function() {
            $(this).children("#bg").stop().animate({opacity:0.5}, 500);
        }, function() {
            $(this).children("#bg").stop().animate({opacity:1}, 500);
        });*/
        StudWorld.main.internalBrowserShow();
        StudWorld.main.registerLinks();

    };
    this.internalBrowserShow = function() {
        $(".cell-"+StudWorld.main.number).snabbt({
            position: [345*StudWorld.main.number -3020, 0, 0],
            easing: 'easeOut'
        });
        StudWorld.main.number++;
        if(StudWorld.main.number>3) {
            StudWorld.main.number = 0;
            return;
        }
        setTimeout(StudWorld.main.internalBrowserShow, 150);
    };
    this.forceCellURL = function(url) {
        for(var i = 0; i < 3; i++) {
            for(var q = 0; q<3;q++) {
                if(StudWorld.main.data[i][q]===undefined) {continue;}
                if(url===StudWorld.main.data[i][q].url) {
                    this.openCell(StudWorld.main.data[i][q]);
                    break;
                }
            }
        }
    };
    this.forceSlideURL = function(url) {
        for(var i = 0; i < this.currentcell.links.length; i++) {
            if(this.currentcell.links[i].url===url) {
                $('#slider').data('rhinoslider').next($('.slide-'+i));
                break;
            }
        }
    };
    this.openCell = function(cell) {
        this.currentcell = cell;
        window.history.pushState('StudWorld - ' + cell.name, 'StudWorld - ' + cell.name, cell.url);
        document.title = 'StudWorld - ' + cell.name;
        $(".interface-right").fadeTo(500, 1, function() {
            $(this).css("display","block");
        });
        $(".browse-bg").fadeTo(500, 0, function() {
            $(this).remove();
        });
        var mc = $('#main-container');
        var tca = $('<div></div>')
                .addClass("caption");
        var ca = $('<div></div>')
                .addClass("links");
        var sl = $('<div></div>')
                .attr("id", "slider");
        for(var i = 0; i < cell.links.length; i++) {
            var link = $('<a></a>')
                .text(cell.links[i].name)
                .attr("href",cell.links[i].url);
            switch(cell.links[i].type) {
                case 0: link.addClass("slide-link"); break;
                case 1: link.addClass("external"); break;
                case 2: link.addClass("browse"); break;
            }
            ca.append(link);
            var slide  = $('<li></li>')
                .css("background-image", "url("+cell.links[i].image+")")
                .css("background-size", "cover")
                .css("background-position", "center")
                .addClass("slide-"+i)
                .append(
                    $('<p></p>').text(cell.links[i].text)
                );
            sl.append(slide);
        }
        tca.append(ca);
        mc.append(tca);
        mc.append(sl);
        $("body").append(mc);
        $('#slider').rhinoslider({
            effect: 'fade',
            effectTime: 500,
            easing: 'linear',
            controlsMousewheel: false,
            controlsKeyboard: false,
            controlsPrevNext: false,
            controlsPlayPause: false,
            animateActive: false,
            showBullets: 'never',
            showControls: 'never'
        });
        mc.fadeTo(500, 1);
        StudWorld.main.registerLinks();
    };
}