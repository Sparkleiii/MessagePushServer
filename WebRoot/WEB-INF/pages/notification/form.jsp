<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Admin Console</title>
    <meta name="menu" content="notification"/>
</head>

<body>

<h1>Send Notifications</h1>

<%--<div style="background:#eee; margin:20px 0px; padding:20px; width:500px; border:solid 1px #999;">--%>
<div style="margin:20px 0px;">
    <form action="notification.do?action=send" method="post" style="margin: 0px;" enctype="multipart/form-data">
        <table width="600" cellpadding="4" cellspacing="0" border="0">
            <tr>
                <td width="20%">To:</td>
                <td width="80%">
                    <input type="radio" name="broadcast" value="0" checked="checked"/> 所有人
                    <input type="radio" name="broadcast" value="1"/> 通过客户端编号
                    <input type="radio" name="broadcast" value="2"/> 通过用户名
                    <input type="radio" name="broadcast" value="3"/> 通过兴趣标签
                </td>
            </tr>
            <tr id="trUsername" style="display:none;">
                <td>客户端编号:</td>
                <td><input type="text" id="username" name="username" value="" style="width:380px;"/></td>
            </tr>
            <tr id="trAlias" style="display:none;">
                <td>用户名:</td>
                <td><input type="text" id="alias" name="alias" value="" style="width:380px;"/></td>
            </tr>
            <tr id="trTag" style="display:none;">
                <td>兴趣标签:</td>
                <td><input type="text" id="tag" name="tag" value="" style="width:380px;"/></td>
            </tr>
            <tr>
                <td>标题:</td>
                <td><input type="text" id="title" name="title" value="2018年全国硕士研究生招生考试考场规则" style="width:380px;"/></td>
            </tr>
            <tr>
                <td>内容:</td>
                <td><textarea id="message" name="message" style="width:380px; height:80px;">一、考生应当自觉服从监考员等考试工作人员管理，不得以任何理由妨碍监考员等考试工作人员履行职责，不得扰乱考场及其他相关工作地点的秩序。

二、考生凭本人《准考证》和有效居民身份证按规定时间和地点参加考试。应当主动接受监考员按规定对其进行的身份验证核查、安全检查和随身物品检查等。

三、考生只准携带省级教育招生考试机构规定的考试用品，如黑色字迹签字笔，以及铅笔、橡皮、绘图仪器等，或者按照招生单位在准考证上注明的所需携带的用具。不得携带任何书刊、报纸、稿纸、图片、资料、具有通讯功能的工具(如手机、照相设备、扫描设备等)或者有存储、编程、查询功能的电子用品以及涂改液、修正带等物品进入考场。

考生在考场内不得传递文具、用品等。

四、考生入场后，对号入座，将《准考证》、有效居民身份证放在桌子左上角以便核验。《准考证》正、反两面在使用期间均不得涂改或书写。考生领到答题卡、答题纸、试卷后，应当在指定位置和规定的时间内准确清楚地填涂姓名、考生编号等信息，按照省级教育招生考试机构的要求粘贴条形码等。凡漏贴条形码的，凡漏填(涂)、错填(涂)或者字迹不清的答卷影响评卷结果，责任由考生自负。

遇试卷、答题卡、答题纸等分发错误及试卷字迹不清、漏印、重印、缺页等问题，可举手询问;涉及试题内容的疑问，不得向监考员询问。

五、开考信号发出后，考生方可开始答题。

六、开考15分钟后，迟到考生不准进入考场参加当科考试，交卷出场时间不得早于当科考试结束前30分钟，具体出场时间由省级教育招生考试机构规定。考生交卷出场后不得再进场续考，也不得在考试机构规定的区域逗留或者交谈。

七、考生应当在答题纸的密封线以外或者答题卡规定的区域答题。不得用规定以外的笔和纸答题，写在草稿纸或者规定区域以外的答案一律无效，不得在答卷、答题卡上做任何标记。答题过程中只能用同一类型和颜色字迹的笔。

八、考生在考场内须保持安静，不准吸烟，不准喧哗，不准交头接耳、左顾右盼、打手势、做暗号，不准夹带、旁窥、抄袭或者有意让他人抄袭，不准传抄试题、答案或者交换试卷、答题卡、答题纸，不准将试卷、答卷、答题卡或者草稿纸带出考场。

九、考试结束信号发出后，考生应当立即停止答题并停笔。

全国统一命题科目的试卷和答题卡放在桌上，由监考员逐一收取。自命题科目，由考生将试卷、答题卡、答题纸(或者答卷)装入原试卷袋内并密封。经监考员逐个核查无误后，方可逐一离开考场。

十、考生不遵守考场规则，不服从考务工作人员管理，有违纪、作弊等行为的，将按照《中华人民共和国教育法》以及《国家教育考试违规处理办法》执行，并将记入国家教育考试考生诚信档案;涉嫌违法的，移送司法机关，依照《中华人民共和国刑法》等追究法律责任。</textarea>
                </td>
            </tr>
            <tr>
                <td>链接地址:</td>
                <td><input type="text" id="uri" name="uri" value="" style="width:380px;"/>
                    <br/><span
                            style="font-size:0.8em">例: http://www.dokdocorea.com, geo:37.24,131.86, tel:111-222-3333</span>
                </td>
            </tr>
            <tr>
                <td>图片:</td>
                <td><input type="file" id="image" name="image" value="" style="width:380px;"/>
                    <br/><span style="font-size:0.8em" style="color:red">只允许图片</span>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    //<![CDATA[

    $(function () {
        $('input[name=broadcast]').click(function () {
            if ($('input[name=broadcast]')[0].checked) {
                $('#trUsername').hide();
                $('#trAlias').hide();
                $('#trTag').hide();
            } else if ($('input[name=broadcast]')[1].checked) {
                $('#trUsername').show();
                $('#trAlias').hide();
                $('#trTag').hide();
            } else if ($('input[name=broadcast]')[2].checked) {
                $('#trUsername').hide();
                $('#trAlias').show();
                $('#trTag').hide();
            } else if ($('input[name=broadcast]')[3].checked) {
                $('#trUsername').hide();
                $('#trAlias').hide();
                $('#trTag').show();
            }
        });

        if ($('input[name=broadcast]')[0].checked) {
            $('#trUsername').hide();
            $('#trAlias').hide();
            $('#trTag').hide();
        } else if ($('input[name=broadcast]')[1].checked) {
            $('#trUsername').show();
            $('#trAlias').hide();
            $('#trTag').hide();
        } else if ($('input[name=broadcast]')[2].checked) {
            $('#trUsername').hide();
            $('#trAlias').show();
            $('#trTag').hide();
        } else if ($('input[name=broadcast]')[3].checked) {
            $('#trUsername').hide();
            $('#trAlias').hide();
            $('#trTag').show();
        }
    });
    //]]>
</script>

</body>
</html>
