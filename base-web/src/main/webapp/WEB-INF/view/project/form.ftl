<@we.html css=[] js=[]>

<div class="page-content">
    <div class="page-header">

    </div><!-- /.page-header -->
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
            <label class="col-sm-3 control-label no-padding-right" for="form-field-5">Grid Sizing</label>

            <div class="col-sm-9">
                <div class="clearfix">
                    <input class="col-xs-5" type="text" id="form-field-5" placeholder=".col-xs-1" />
                </div>

                <div class="space-2"></div>

            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" >Tag input</label>
            <div class="col-sm-9">
                <textarea class="col-xs-5" id="form-field-8" placeholder="Default Text"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" >Tag input</label>
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
            <label class="col-sm-3 control-label no-padding-right" >Chosen</label>
            <div class="col-sm-9">
                <select class="chosen-select col-xs-5" id="form-field-select-3" data-placeholder="Choose a Country..." style="display: none;">
                    <option value="">&nbsp;</option>
                    <option value="AL">Alabama</option>
                    <option value="AK">Alaska</option>
                    <option value="AZ">Arizona</option>
                    <option value="AR">Arkansas</option>
                    <option value="CA">California</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" >Multiple</label>
            <div class="col-sm-9">
                <select multiple="" class="chosen-select col-xs-5" id="form-field-select-4" data-placeholder="Choose a Country...">
                    <option value="">&nbsp;</option>
                    <option value="AL">Alabama</option>
                    <option value="AK">Alaska</option>
                    <option value="AZ">Arizona</option>
                    <option value="AR">Arkansas</option>
                    <option value="CA">California</option>
                    <option value="CO">Colorado</option>
                    <option value="CT">Connecticut</option>
                </select>
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
</div>


</@we.html>

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

});
</script>