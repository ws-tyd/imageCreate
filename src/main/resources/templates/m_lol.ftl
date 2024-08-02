<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>海报</title>
    <style>
        body {
        }
        .MainBox {
            position: relative;
            background-image: url("http://localhost:9999/static/img/games/17/lolm0.png");
            width: 960px;
            height: 630px;
        }
        .MainHead{

        }
        .fontTheme {
            z-index: 999;
            font-weight: bold;
            color: white;
            -webkit-text-stroke: 1px #004af3;
            text-shadow: 5px 4px 3px #ff4dbd;
        }

        .titleBox {
            text-align: center;
            width: 230px;
            height: 30px;
            line-height: 30px;
        }
        .MainDetail{
        }
        .detailBox{
            width: 130px;
            height: 185px;
            border: #1BA035 1px solid;
        }

    </style>
</head>
<body>
<div class="MainBox">
    <div class="MainHead">
        <div class="titleBox fontTheme" style="position: absolute;top:88px;left: 30px">QQ号:${account.qq}</div>
        <div class="titleBox fontTheme" style="position: absolute;top:40px;left: 390px">英雄数量:${account.heroesAmount}</div>
        <div class="titleBox fontTheme" style="position: absolute;top:40px;left: 665px">皮肤数量:${account.skinsAmount}</div>
        <div class="titleBox fontTheme" style="position: absolute;top:88px;left: 390px">游戏区服:${account.serverArea}</div>
        <div class="titleBox fontTheme" style="position: absolute;top:88px;left: 665px">可二次</div>
    </div>
    <div class="MainDetail" style="display: flex;flex-wrap: wrap;padding: 180px 60px;">
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
        <div class="detailBox" ></div>
    </div>
<#--    <div class="MainDetail">-->
<#--        <div class="detailBox" style="position: absolute;top: 185px;left: 67px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 185px;left: 242px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 185px;left: 417px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 185px;left: 592px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 185px;left: 767px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 400px;left: 67px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 400px;left: 242px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 400px;left: 417px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 400px;left: 592px"></div>-->
<#--        <div class="detailBox" style="position: absolute;top: 400px;left: 767px"></div>-->
<#--    </div>-->
</div>
</body>
</html>
