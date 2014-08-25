<@we.html css=[] js=[]>
<link rel="stylesheet" href="/assets/css/chosen.css" />
<link rel="stylesheet" href="/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="/assets/css/colorpicker.css" />

<script src="/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="/assets/js/chosen.jquery.min.js"></script>
<script src="/assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="/assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="/assets/js/date-time/moment.min.js"></script>
<script src="/assets/js/date-time/daterangepicker.min.js"></script>
<script src="/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="/assets/js/jquery.knob.min.js"></script>
<script src="/assets/js/jquery.autosize.min.js"></script>
<script src="/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="/assets/js/jquery.maskedinput.min.js"></script>
<script src="/assets/js/bootstrap-tag.min.js"></script>

<div class="row">
<div class="col-xs-12">
<!-- PAGE CONTENT BEGINS -->

<form class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>

        <div class="col-sm-9">
            <input type="text" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-5" />
        </div>
    </div>

    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Password Field </label>

        <div class="col-sm-9">
            <input type="password" id="form-field-2" placeholder="Password" class="col-xs-10 col-sm-5" />
                <span class="help-inline col-xs-12 col-sm-7">
                    <span class="middle">Inline help text</span>
                </span>
        </div>
    </div>

    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly"> Readonly field </label>

        <div class="col-sm-9">
            <input readonly="" type="text" class="col-xs-10 col-sm-5" id="form-input-readonly" value="This text field is readonly!" />
                <span class="help-inline col-xs-12 col-sm-7">
                    <label class="middle">
                        <input class="ace" type="checkbox" id="id-disable-check" />
                        <span class="lbl"> Disable it!</span>
                    </label>
                </span>
        </div>
    </div>

    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-4">Relative Sizing</label>

        <div class="col-sm-9">
            <input class="input-sm" type="text" id="form-field-4" placeholder=".input-sm" />
            <div class="space-2"></div>

            <div class="help-block" id="input-size-slider"></div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-5">Grid Sizing</label>

        <div class="col-sm-9">
            <div class="clearfix">
                <input class="col-xs-1" type="text" id="form-field-5" placeholder=".col-xs-1" />
            </div>

            <div class="space-2"></div>

            <div class="help-block" id="input-span-slider"></div>
        </div>
    </div>

    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Tag input</label>
        <div class="col-sm-9">
            <textarea class="col-xs-5" id="form-field-8" placeholder="Default Text"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Tag input</label>
        <div class="col-sm-9">
            <select class="chosen-container chosen-container-single col-xs-5" id="form-field-select-1">
                <option value="">&nbsp;</option>
                <option value="AL">Alabama</option>
                <option value="AK">Alaska</option>
                <option value="AZ">Arizona</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Chosen</label>
        <div class="col-sm-9">
            <select class=" chosen-select" id="form-field-select-3" data-placeholder="Choose a Country..." style="display: none;">
                <option value="">&nbsp;</option>
                <option value="AL">Alabama</option>
                <option value="AK">Alaska</option>
                <option value="AZ">Arizona</option>
                <option value="AR">Arkansas</option>
                <option value="CA">California</option>
            </select>
            <div class="chosen-container chosen-container-single" style="width: 305px;" title="" id="form_field_select_3_chosen">
                <a class="chosen-single" tabindex="-1">
                    <span>Alaska</span>
                    <div>
                        <b></b>
                    </div>
                </a>
                <div class="chosen-drop">
                    <div class="chosen-search">
                        <input type="text" autocomplete="off">
                    </div>
                    <ul class="chosen-results">
                        <li class="active-result result-selected" style="" data-option-array-index="0">&nbsp;</li>
                        <li class="active-result" style="" data-option-array-index="1">Alabama</li>
                        <li class="active-result result-selected" style="" data-option-array-index="2">Alaska</li>
                        <li class="active-result" style="" data-option-array-index="3">Arizona</li>
                        <li class="active-result" style="" data-option-array-index="4">Arkansas</li>
                        <li class="active-result" style="" data-option-array-index="5">California</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" type="button">
                <i class="icon-ok bigger-110"></i>
                     提交
            </button>
            &nbsp; &nbsp; &nbsp;
            <button class="btn" type="reset">
                <i class="icon-undo bigger-110"></i>
                返回
            </button>
        </div>
    </div>
</form>

</div><!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
jQuery(function($) {
    $('#id-disable-check').on('click', function() {
        var inp = $('#form-input-readonly').get(0);
        if(inp.hasAttribute('disabled')) {
            inp.setAttribute('readonly' , 'true');
            inp.removeAttribute('disabled');
            inp.value="This text field is readonly!";
        }
        else {
            inp.setAttribute('disabled' , 'disabled');
            inp.removeAttribute('readonly');
            inp.value="This text field is disabled!";
        }
    });


    $(".chosen-select").chosen();
    $('#chosen-multiple-style').on('click', function(e){
        var target = $(e.target).find('input[type=radio]');
        var which = parseInt(target.val());
        if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
        else $('#form-field-select-4').removeClass('tag-input-style');
    });
//    $('textarea[class*=autosize]').autosize({append: "\n"});
//    $('textarea.limited').inputlimiter({
//        remText: '%n character%s remaining...',
//        limitText: 'max allowed : %n.'
//    });

//    $.mask.definitions['~']='[+-]';
//    $('.input-mask-date').mask('99/99/9999');
//    $('.input-mask-phone').mask('(999) 999-9999');
//    $('.input-mask-eyescript').mask('~9.99 ~9.99 999');
//    $(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});



//    $( "#input-size-slider" ).css('width','200px').slider({
//        value:1,
//        range: "min",
//        min: 1,
//        max: 8,
//        step: 1,
//        slide: function( event, ui ) {
//            var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
//            var val = parseInt(ui.value);
//            $('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
//        }
//    });

//    $( "#input-span-slider" ).slider({
//        value:1,
//        range: "min",
//        min: 1,
//        max: 12,
//        step: 1,
//        slide: function( event, ui ) {
//            var val = parseInt(ui.value);
//            $('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
//        }
//    });


//    $( "#slider-range" ).css('height','200px').slider({
//        orientation: "vertical",
//        range: true,
//        min: 0,
//        max: 100,
//        values: [ 17, 67 ],
//        slide: function( event, ui ) {
//            var val = ui.values[$(ui.handle).index()-1]+"";
//
//            if(! ui.handle.firstChild ) {
//                $(ui.handle).append("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
//            }
//            $(ui.handle.firstChild).show().children().eq(1).text(val);
//        }
//    }).find('a').on('blur', function(){
//                $(this.firstChild).hide();
//            });
//
//    $( "#slider-range-max" ).slider({
//        range: "max",
//        min: 1,
//        max: 10,
//        value: 2
//    });

//    $( "#eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
//        // read initial values from markup and remove that
//        var value = parseInt( $( this ).text(), 10 );
//        $( this ).empty().slider({
//            value: value,
//            range: "min",
//            animate: true
//
//        });
//    });


//    $('#id-input-file-1 , #id-input-file-2').ace_file_input({
//        no_file:'No File ...',
//        btn_choose:'Choose',
//        btn_change:'Change',
//        droppable:false,
//        onchange:null,
//        thumbnail:false //| true | large
//        //whitelist:'gif|png|jpg|jpeg'
//        //blacklist:'exe|php'
//        //onchange:''
//        //
//    });

//    $('#id-input-file-3').ace_file_input({
//        style:'well',
//        btn_choose:'Drop files here or click to choose',
//        btn_change:null,
//        no_icon:'icon-cloud-upload',
//        droppable:true,
//        thumbnail:'small'//large | fit
//        //,icon_remove:null//set null, to hide remove/reset button
//        /**,before_change:function(files, dropped) {
//						//Check an example below
//						//or examples/file-upload.html
//						return true;
//					}*/
//        /**,before_remove : function() {
//						return true;
//					}*/
//        ,
//        preview_error : function(filename, error_code) {
//            //name of the file that failed
//            //error_code values
//            //1 = 'FILE_LOAD_FAILED',
//            //2 = 'IMAGE_LOAD_FAILED',
//            //3 = 'THUMBNAIL_FAILED'
//            //alert(error_code);
//        }
//
//    }).on('change', function(){
//                //console.log($(this).data('ace_input_files'));
//                //console.log($(this).data('ace_input_method'));
//            });


    //dynamically change allowed formats by changing before_change callback function
//    $('#id-file-format').removeAttr('checked').on('change', function() {
//        var before_change
//        var btn_choose
//        var no_icon
//        if(this.checked) {
//            btn_choose = "Drop images here or click to choose";
//            no_icon = "icon-picture";
//            before_change = function(files, dropped) {
//                var allowed_files = [];
//                for(var i = 0 ; i < files.length; i++) {
//                    var file = files[i];
//                    if(typeof file === "string") {
//                        //IE8 and browsers that don't support File Object
//                        if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
//                    }
//                    else {
//                        var type = $.trim(file.type);
//                        if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
//                                || ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
//                                ) continue;//not an image so don't keep this file
//                    }
//
//                    allowed_files.push(file);
//                }
//                if(allowed_files.length == 0) return false;
//
//                return allowed_files;
//            }
//        }
//        else {
//            btn_choose = "Drop files here or click to choose";
//            no_icon = "icon-cloud-upload";
//            before_change = function(files, dropped) {
//                return files;
//            }
//        }
//        var file_input = $('#id-input-file-3');
//        file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
//        file_input.ace_file_input('reset_input');
//    });




});
</script>
</@we.html>