
function getPhonesByBrandInList(){
    fetch("/collections/brands", {
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(res=>res.json())
        .then(data=>{
            const ul = $(".list-menu-nav")
            data.forEach(brand=>{
                const li_ul = $("<li class='mn-item active-nav has-child'></li>")
                const a_li_ul = "<a href='/collections/brand?name="+brand.name+"' class='menu-item__link item-link' "+
                                   "title='"+brand.name+"'>"+
                    "<img width='24' height='24' src='https://file.hstatic.net/1000379792/file/menu_icon_3_18fcbcc806f6438888c40d93fc67ae06.svg' alt='Điện thoại' />"+
                    "<span>"+brand.name+"</span>"+
                    "<i class='float-right arrow-right'></i>"+
                "</a>"
                const div_li_ul = $("<div class='submenu scroll'>" +
                    "                       <div class='view-all visible-xs visible-sm'>" +
                    "                           <a href='/collections/brand?name="+brand.name+"'>Xem tất cả »</a>" +
                    "                       </div></div>")
                const ul_div_li_ul = $("<ul class='submenu__list'></ul>")

                const url = new URL("/collections", window.location.origin);
                url.searchParams.append('nameBrand', brand.name);
                url.searchParams.append('page', 0);

                const request = new Request(url, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                fetch(request)
                    .then(res => res.json())
                    .then(phones => {
                        phones.forEach(phone=>{
                            const a_li_ul_div_li_ul = "<li class='item-lv1'>" +
                                "<a href=`/phone?id="+phone.id+"` class='item-link'>"+phone.name+"</a>" +
                                "</li>"
                            ul_div_li_ul.append(a_li_ul_div_li_ul)
                            console.log(ul_div_li_ul)
                        })
                    })
                    .catch(error => {
                        console.log(error);
                    });

                div_li_ul.append(ul_div_li_ul)
                li_ul.append(a_li_ul)
                li_ul.append(div_li_ul)
                $(ul).append(li_ul)
            })
        })
        .catch(error=>{
            console.log(error)
        })
}

function getPhonesByUrl(nameClass, url){
    fetch('collections/'+url, {
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res=> res.json())
        .then(data=>{
            const new_product = $("."+nameClass)
            data.forEach(phone=>{
                const div = $("<div class='d-flex-column item-owl pd-left--10'></div>")
                const div_block = $("<div class='product-block item'></div>")

                const div_img = $("<div class='product-img has-hover' data-frame='frame1'></div>")
                const div_detail = "<div class='product-detail'>" +
                    "                           <h3 class='pro-name'>" +
                    "                               <a href='/phone?id="+phone.id+"'" +
                    "                               title='"+phone.name+"'>" +
                    "                               "+phone.name+"" +
                    "                               </a>" +
                    "                           </h3>" +
                    "                           <div class='d-flex js-between d-flex-center'>" +
                    "                                <ul class='list-variants d-flex d-flex-wrap image'>" +
                    "                                    <li>+5 màu sắc</li>" +
                    "                                 </ul>" +
                    "                            </div>" +
                    "                            <div class='box-pro-prices'>" +
                    "                                 <p class='pro-price'>" +
                    "                                    <span> "+phone.phoneDetails[0].price+" </span>" +
                    "                                    <del class='compare-price'>19,690,000₫</del>" +
                    "                                  </p>" +
                    "                             </div>" +
                    "                             <ul class='hash-tag-loop'>" +
                    "                                 <li>" +
                    "                                      Nhập tag hashtag_nội dung hiển thị để show nội" +
                    "                                      dung này" +
                    "                                 </li>" +
                    "                              </ul>" +
                    "                           </div>"
                
                const a = $("<a href='/phone?id="+phone.id+"' title='"+phone.name+"' class='image-resize'></a>")
                
                a.append("<div class='product-sale'><span>phone.voucher</span></div>")
                phone.phoneDetails.forEach(phoneDetail=>{
                    const img = "<img class='lazyload dt-width-100 img-first' width='260' height='260'" +
                        "                    src='images/phones/"+phoneDetail.img+"'" +
                        "                    alt='"+phone.name+"' />"
                    a.append(img)
                })

                const div_div = "<div class='button-loop-pro hidden-xs hidden-sm'>" +
                    "                        <button class='btn-quickview'" +
                    "                                onclick='window.wd.scofield.quickview('/products/iphone-11-chinh-hang-vna?view=quickview-nochoose')'" +
                    "                                type='button'>" +
                    "                        <img src='https://file.hstatic.net/200000525917/file/search-icon_61351aaf4f2a4ba0b163434492c75c0d.svg'" +
                    "                             width='16' height='16' alt='Xem nhanh' />" +
                    "                        </button>" +
                    "                   </div>"
                div_img.append(a)
                div_img.append(div_div)

                div_block.append(div_img)
                div_block.append(div_detail)

                div.append(div_block)

                new_product.append(div)
            })

        })
        .catch(error=>console.log(error))
}

function getPhonesByBrands(){
    fetch('collections/brands', {
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(res=>res.json())
        .then(data=>{
            const main = $("main")
            let count = 1
            data.forEach(brand=>{

                const section = $("<section class='section_collection_group pd-top-30' data-include='section-collection-group-"+count+"'></section>")
                const div_container = $("<div class='container'></div>")
                const div_color = $("<div class='bg-color-while'></div>")
                const div_section_buttons = $("<div class='wd-top-title d-flex d-flex-center js-between'></div>")
                const div_section_phones = $("<div class='d-flex d-flex-wrap row-left-list'></div>")
                const div_section_see_all = "<div class='text-center btn-view-all-tab'>" +
                    "                           <a class='btn btn-all-tab' href='/collections/brand?name="+brand.name+"'>" +
                    "                            Xem tất cả »" +
                    "                        </a></div>"

                const ul_dic_section_buttons = $("<ul class='menu-col d-flex scroll-x-mb scroll-x-tablet'></ul>")
                const url = new URL("/collections", window.location.origin);
                url.searchParams.append('nameBrand', brand.name);
                url.searchParams.append('page', 0);

                const request = new Request(url, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                fetch(request)
                    .then(res => res.json())
                    .then(phones => {

                        phones.forEach(phone=>{
                            const div_div = $("<div" +
                                "                  class='d-flex-column mg-bottom-15 mg-bottom-10-mb col-lg-1 col-md-3 col-sm-4 col-xs-6 pd-right-0'></div>")

                            const div_div_div = $("<div class='product-block item loop-border'></div>")
                            const div_img = $("<div class='product-img has-hover' data-frame='frame1'></div>")
                            const a = $("<a href='/phone?id="+phone.id+"' title='"+phone.name+"' class='image-resize'></a>")

                            a.append("<div class='product-sale'><span>phone.voucher</span></div>")
                            phone.phoneDetails.forEach(phoneDetail=>{
                                const img = "<img class='lazyload dt-width-100 img-first' width='260' height='260'" +
                                    "                    src='images/phones/"+phoneDetail.img+"'" +
                                    "                    alt='"+phone.name+"' />"
                                a.append(img)
                            })
                            div_img.append(a)

                            const div_detail = "<div class='product-detail'>" +
                                "                           <h3 class='pro-name'>" +
                                "                               <a href='/phone?id="+phone.id+"'" +
                                "                               title='"+phone.name+"'>" +
                                "                               "+phone.name+"" +
                                "                               </a>" +
                                "                           </h3>" +
                                "                           <div class='d-flex js-between d-flex-center'>" +
                                "                                <ul class='list-variants d-flex d-flex-wrap image'>" +
                                "                                    <li>+5 màu sắc</li>" +
                                "                                 </ul>" +
                                "                            </div>" +
                                "                            <div class='box-pro-prices'>" +
                                "                                 <p class='pro-price'>" +
                                "                                    <span> "+phone.phoneDetails[0].price+" </span>" +
                                "                                    <del class='compare-price'>19,690,000₫</del>" +
                                "                                  </p>" +
                                "                             </div>" +
                                "                             <ul class='hash-tag-loop'>" +
                                "                                 <li>" +
                                "                                      Nhập tag hashtag_nội dung hiển thị để show nội" +
                                "                                      dung này" +
                                "                                 </li>" +
                                "                              </ul>" +
                                "                           </div>"
                            div_div_div.append(div_img)
                            div_div_div.append(div_detail)
                            div_div.append(div_div_div)
                            div_section_phones.append(div_div)
                        })
                    })
                    .catch(error => {
                        console.log(error);
                });

                ul_dic_section_buttons.append("<li>" +
                    "                 <a href='/collections/brand?name="+brand.name+"'>"+brand.name+"</a>" +
                    "              </li>")

                div_section_buttons.append("<h2 class='title-section'>"+brand.name+"</h2>")
                div_section_buttons.append(ul_dic_section_buttons)

                div_color.append(div_section_buttons)
                div_color.append(div_section_phones)
                div_color.append(div_section_see_all)
                div_container.append(div_color)
                section.append(div_container)
                main.append(section)
            })
            count += 1
        })
}
document.addEventListener('DOMContentLoaded', function(){
    getPhonesByBrandInList()
    getPhonesByUrl("newest", "newest?page=0&number=5")
    getPhonesByUrl("best-selling", "best-selling?page=0&number=5")
    getPhonesByBrands()
})
