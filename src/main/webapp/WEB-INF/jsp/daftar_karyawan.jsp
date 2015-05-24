<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : pegawaiTambah
    Created on : May 6, 2015, 3:34:56 PM
    Author     : fas
--%>
<%@include file="/konfig/konfig.jsp" %>
<%@include file="/WEB-INF/layout/header.jsp" %>

<div class="row">
    <table class="table">
        <tr>
            <td>
<!--                <h1>Daftar Karyawan <button type="submit" class="btn btn-success text-right" >Go</button></h1>-->
                    <h1>
                        <span>Daftar Karyawan</span>
                        <button type="submit" class="btn btn-success pull-right">Tambah Karyawan</button>
                    </h1>
            </td>
<!--            <td class="text-right " >
                <button type="submit" class="btn btn-success text-right" >Go</button>
            </td>-->
        </tr>
            
                
    </table>  
    <table class="table table-striped">
        <thead>
            <tr>
                <th colspan="1"></th>
                <th colspan="1"></th>
                <th colspan="3">Nama Lengkap</th>
                <th colspan="1">Kontrak ke-</th>
                <th colspan="1">Kontrak Mulai</th>
                <th colspan="1">Kontrak Berakhir</th>
                <th colspan="1">Lama Kontrak</th>
                <th colspan="1">Gaji Pokok</th>
                <th colspan="1">Total Hari</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${karyawans}" var="karyawans" varStatus="loop">
                <tr data-toggle="collapse" data-target="#accordion<c:out value="${loop.index}" />" class="clickable">
                    <td colspan="1">
                        <c:choose>
                            <c:when test="${karyawans.warning_type == '2'}">
                                <span class="glyphicon glyphicon-warning-sign glyphicon-warning-contract-end"></span>
                            </c:when>
                            <c:when test="${karyawans.warning_type == '1'}">
                                <span class="glyphicon glyphicon-warning-sign glyphicon-warning-almost-due"></span>
                            </c:when>
                            <c:when test="${karyawans.warning_type == '0'}">
                            </c:when>
                        </c:choose>
                    </td>
                    <td colspan="1">></td>
                    <td colspan="3"><c:out value="${karyawans.nama_karyawan}" /></td>
                    <td colspan="1"><c:out value="${karyawans.jumlah_kontrak}" /></td>
                    <td colspan="1"><c:out value="${karyawans.kontrak_mulai}" /></td>
                    <td colspan="1"><c:out value="${karyawans.kontrak_berakhir}" /></td>
                    <td colspan="1"><c:out value="${karyawans.lama_kontrak}" /></td>
                    <td colspan="1">350,000</td>
                    <td colspan="1"><c:out value="${karyawans.total_hari}" /></td>
                </tr>
                <tr>
                    <td colspan="9">
                        <div id="accordion<c:out value="${loop.index}" />" class="collapse">
                            <table>
                                <tr>
                                    <td>Alamat</td>
                                    <td>  :  </td>
                                    <td><c:out value="${karyawans.alamat}" /></td>
                                </tr>
                                <tr>
                                    <td>Tempat/Tgl Lahir  </td>
                                    <td>:</td>
                                    <td><c:out value="${karyawans.tempat_lahir}" />, <c:out value="${karyawans.tgl_lahir}" /></td>
                                </tr>
                                <tr>
                                    <td>No KTP </td>
                                    <td>  :  </td>
                                    <td><c:out value="${karyawans.no_ktp}" /></td>
                                </tr>
                                <tr>
                                    <td>Bagian</td>
                                    <td>  :  </td>
                                    <td><c:out value="${karyawans.bagian}" /></td>
                                </tr>
                                <tr>
                                    <td>Keterangan</td>
                                    <td>  :  </td>
                                    <td><c:out value="${karyawans.keterangan}" /></td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
<!--            <tr data-toggle="collapse" data-target="#accordionOne" class="clickable">
                <td colspan="1"><span class="glyphicon glyphicon-warning-sign glyphicon-warning-contract-end"></span></td>
                <td colspan="1">></td>
                <td colspan="3"> Yotsuba</td>
                <td colspan="1">2</td>
                <td colspan="1">12/01/2015</td>
                <td colspan="1">01/03/2015</td>
                <td colspan="1">48</td>
                <td colspan="1">350,000</td>
                <td colspan="1">70</td>
            </tr>
            <tr>
                <td colspan="9">
                    <div id="accordionOne" class="collapse">
                        <table>
                            <tr>
                                <td>Alamat</td>
                                <td>  :  </td>
                                <td>Jl. Gatotkaca Terbang Tinggi 1</td>
                            </tr>
                            <tr>
                                <td>Tempat/Tgl Lahir  </td>
                                <td>:</td>
                                <td>Gresik, 9 Maret 1990</td>
                            </tr>
                            <tr>
                                <td>No KTP </td>
                                <td>  :  </td>
                                <td>1234567890</td>
                            </tr>
                            <tr>
                                <td>Bagian</td>
                                <td>  :  </td>
                                <td>Printing</td>
                            </tr>
                            <tr>
                                <td>Keterangan</td>
                                <td>  :  </td>
                                <td>Lorem ipsum doler sit amet. kamehamehameha.</td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr data-toggle="collapse" data-target="#accordionTwo" class="clickable">
                <td colspan="1"><span class="glyphicon glyphicon-warning-sign glyphicon-warning-almost-due"></span></td>
                <td colspan="1">></td>
                <td colspan="3">Makabe</td>
                <td colspan="1">1</td>
                <td colspan="1">03/02/2015</td>
                <td colspan="1">11/05/2015</td>
                <td colspan="1">97</td>
                <td colspan="1">650,000</td>
                <td colspan="1">97</td>
            </tr>
            <tr>
                <td colspan="9">
                    <div id="accordionTwo" class="collapse">Hidden by default</div>
                </td>
            </tr>-->
        </tbody>
    </table>
</div>

<%@include file="/WEB-INF/layout/footer.jsp" %>
